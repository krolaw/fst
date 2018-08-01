import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
//import java.awt.Robot;
import java.awt.Toolkit;
//import java.awt.event.InputEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FST extends JFrame implements Runnable {
	private static boolean configChanged = false;
	static boolean canSave = false;
	final static String VERSION = "V1.49b";
	final static int RANDOM = 0;
	final static int LEFT = 1;
	final static int CENTER = 2;
	final static int RIGHT = 3;
	final static int FORWARD = 0;
	final static int REVERSE = 1;
	final static int RND_INNER = 2;
	final static int RND = 3;
	final static int JOIN = 4;
	final static String[] ORDER_STRINGS = {"forward", "reverse", "rndinner", "random", "join"};
	final static String[] FONT_STYLES_STRINGS = {"plain", "bold", "italic", "bolditalic"};
	final static String[] H_ALIGN_STRINGS = {"random", "left", "center", "right"};
	final static String[] V_ALIGN_STRINGS = {"random", "top", "center", "bottom"};
	final static String[] CONFIG_WINDOW_STRINGS = { "false","true","minimized" };
	final static String[] MESSAGE_ORDER_STRINGS = { "random", "sequential"};
	static List<String> messages = new ArrayList<String>();
	static Font font = new Font("sans-serif", Font.PLAIN, 35);
	static Color fontColour = new Color(0, 0, 255, 30);
	static int display = 100, delay = 300;
	static int placementX = RANDOM,  placementY = RANDOM;
	static int marginX = 40,  marginY = 40;
	static int letterOrder = FORWARD;
	static int messageOrder = RANDOM;
	static int wordOrder = FORWARD;
	static int configWindow = 1;
	static long nextUpdate = System.currentTimeMillis()/1000L+30L*86400L;
	static boolean checkUpdates = true;
        //static int mouseR = 0, mouseP = 0; // For mouse click throughs
	//private Image background = null;
        private String text;
        private FontMetrics fontMetrics;
        
        private int ascent = 0;
        private int x = 0;
        
        final static File saveDir = new File(System.getProperty("os.name").toUpperCase().contains("WIN")?
                System.getenv("APPDATA") : System.getProperty("user.home"));

	final static File configFile = new File(saveDir,"FSTConfig.xml");
	private static Config config;
	
	public static void main(String[] args) {
		parseConfigFile();
		if (configWindow>0) {
			config = new Config();
		}
		//settingsChanged = false;
		new FST();
	}
        
        public static void settingsChanged() {
            configChanged = true;
        }
	
	protected static void minimise() {
		config.setExtendedState(JFrame.ICONIFIED);
	}

	protected static int matchOption(String match, String[] matches, int def) {
		for (int x = 0; x < matches.length; x++) {
			if (matches[x].equalsIgnoreCase(match)) {
				return x;
			}
		}
		return def;
	}

	public static String join(Collection s, String delimiter) {
		StringBuilder buffer = new StringBuilder();
		Iterator iter = s.iterator();
		if (iter.hasNext()) {
			buffer.append(iter.next());
			while (iter.hasNext()) {
				buffer.append(delimiter);
				buffer.append(iter.next());
			}
		}
		return buffer.toString();
	}

	protected static String orderMessage(String msg) {
		if (wordOrder == FORWARD && letterOrder == FORWARD) {
			return msg;
		}
		List<String> words;
		if (wordOrder == JOIN) {
			words = new ArrayList<String>(1);
			words.add(msg);
		} else {
			words = Arrays.asList(msg.split(" "));
		}
		orderList(words, wordOrder);
		if (letterOrder != FORWARD) {
			for (int x = 0; x < words.size(); x++) {
				char[] l = words.get(x).toCharArray();
				List<Character> letters = new ArrayList<Character>(l.length);
				for (Character c : l) {
					letters.add(c);
				}
				orderList(letters, letterOrder);
				words.set(x, join(letters, ""));
			}
		}
		return join(words, " ");
	}

	private static void orderList(List msg, int mode) {
		if(msg.size()>1) {
			switch (mode) {
			case REVERSE:
				Collections.reverse(msg);
				break;
			case RND:
				Collections.shuffle(msg);
				break;
			case RND_INNER:
				if (msg.size() > 3) {
					Collections.shuffle(msg.subList(1, msg.size() - 1));
				}
				break;
			}
		}
	}

	private static DocumentBuilder documentBuilder = null;
	private static Transformer transformer = null;

	public static void save() {
		if(configChanged && canSave) 
                    configChanged = false;
			try {
				if(documentBuilder == null) {
					documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					transformer = TransformerFactory.newInstance().newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				}
				Document doc = documentBuilder.newDocument();
				Element top = doc.createElement("fst");
				top.setAttribute("author", "http://richard.warburton.it");
				Element startupNode = doc.createElement("startup");
				startupNode.setAttribute("configWindow", CONFIG_WINDOW_STRINGS[configWindow]);
				startupNode.setAttribute("checkUpdates", checkUpdates?"true":"false");
				startupNode.setAttribute("nextUpdate", Long.toString(nextUpdate));
				top.appendChild(startupNode);

				Element fontNode = doc.createElement("font");
				fontNode.setAttribute("color", String.format("#%02X%02X%02X%02X", fontColour.getRed(), fontColour.getGreen(), fontColour.getBlue(), fontColour.getAlpha()));
				fontNode.setAttribute("size", Integer.toString(font.getSize()));
				fontNode.setAttribute("family", font.getFamily());
				fontNode.setAttribute("style", FONT_STYLES_STRINGS[font.getStyle()]);
				top.appendChild(fontNode);

				Element orderNode = doc.createElement("order");
				orderNode.setAttribute("word", ORDER_STRINGS[wordOrder]);
				orderNode.setAttribute("letter", ORDER_STRINGS[letterOrder]);
				orderNode.setAttribute("message", MESSAGE_ORDER_STRINGS[messageOrder]);
				top.appendChild(orderNode);

				Element timingNode = doc.createElement("timing");
				timingNode.setAttribute("on", Integer.toString(display));
				timingNode.setAttribute("off", Integer.toString(delay));
				top.appendChild(timingNode);

				Element positionNode = doc.createElement("position");
				positionNode.setAttribute("x", H_ALIGN_STRINGS[placementX]);
				positionNode.setAttribute("y", V_ALIGN_STRINGS[placementY]);
				positionNode.setAttribute("x-margin", Integer.toString(marginX));
				positionNode.setAttribute("y-margin", Integer.toString(marginY));
				top.appendChild(positionNode);

				for (String msg : messages) {
					Element messageNode = doc.createElement("message");
					messageNode.setTextContent(msg);
					top.appendChild(messageNode);
				}

				transformer.transform(new DOMSource(top), new StreamResult(configFile));

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error: Saving FSTConfig.xml - "+ ex);
			}
		}
	

	private static void parseConfigFile() {
		try { // Parse xml file
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(configFile);
			try { // Check Data
				NodeList nl = doc.getElementsByTagName("message");
				messages = new ArrayList<String>(nl.getLength());
				for (int x = 0; x < nl.getLength(); x++) {
					messages.add(nl.item(x).getTextContent());
				}
				Element topElement = (Element) doc.getElementsByTagName("fst").item(0);
				
				if (!(topElement.getAttribute("author").equals("http://richard.warburton.it"))) {
					throw new NullPointerException("Correct author missing");
				}
				try {
					Element startup = (Element) doc.getElementsByTagName("startup").item(0);
					nextUpdate = getLong(startup.getAttribute("nextUpdate"),nextUpdate);
					configWindow = matchOption(startup.getAttribute("configWindow"), CONFIG_WINDOW_STRINGS, configWindow);
					checkUpdates = ! "false".equalsIgnoreCase(startup.getAttribute("checkUpdates"));
				} catch(Exception e) {}
				
				try {
					Element fontInfo = (Element) doc.getElementsByTagName("font").item(0);
					font = new Font(def(fontInfo.getAttribute("family"), font.getFamily()),
						matchOption(fontInfo.getAttribute("style"), FONT_STYLES_STRINGS, font.getStyle()),
						getInt(fontInfo.getAttribute("size"), font.getSize()));
					try {
						String colour = fontInfo.getAttribute("color");
						Color fColour = Color.decode(colour.substring(0,7));
						fontColour = new Color(fColour.getRed(), fColour.getGreen(), fColour.getBlue(),
							colour.length()==9?Integer.parseInt(colour.substring(7,9),16):
								getInt(fontInfo.getAttribute("alpha"), fontColour.getAlpha()));
					} catch (NumberFormatException ne) {}
				} catch (Exception e) {}

				try {
					Element timingInfo = (Element) doc.getElementsByTagName("timing").item(0);
					display = getInt(timingInfo.getAttribute("on"), display);
					delay = getInt(timingInfo.getAttribute("off"), delay);
				} catch (Exception e) {}

				try {
					Element orderInfo = (Element) doc.getElementsByTagName("order").item(0);
					wordOrder = matchOption(orderInfo.getAttribute("word"), ORDER_STRINGS, wordOrder);
					letterOrder = matchOption(orderInfo.getAttribute("letter"), ORDER_STRINGS, letterOrder);
					messageOrder = matchOption(orderInfo.getAttribute("message"), MESSAGE_ORDER_STRINGS, messageOrder);
				} catch (Exception e) {
				}

				try {
					Element placement = (Element) doc.getElementsByTagName("position").item(0);
					placementX = matchOption(placement.getAttribute("x"), H_ALIGN_STRINGS, placementX);
					placementY = matchOption(placement.getAttribute("y"), V_ALIGN_STRINGS, placementY);
					marginX = getInt(placement.getAttribute("x-margin"), marginX);
					marginY = getInt(placement.getAttribute("y-margin"), marginY);
				} catch (Exception e) {
				}

			} catch (NullPointerException ne) {
				JOptionPane.showMessageDialog(null, "Error: " + ne.getMessage() + "\nA new config file will be created.");
			}
		} catch (SAXException ex) {
			JOptionPane.showMessageDialog(null, "Error: FSTConfig.xml could not be parsed.  A new config file will be created.");
		} catch (IOException ex) {
		} catch (ParserConfigurationException ex) {
			JOptionPane.showMessageDialog(null, "Error: FSTConfig.xml could not be parsed.  A new config file will be created.");
		}
                if (messages.isEmpty()) messages.add("Test Message");
	}

	public static int getInt(String value, int def) {
		try {
			return (int) Integer.parseInt(value);
		} catch (NumberFormatException n) {
			return def;
		}
	}

	public static long getLong(String value, long def) {
		try {
			return (long) Long.parseLong(value);
		} catch (NumberFormatException n) {
			return def;
		}
	}
	
	public static String def(String exists, String def) {
		return exists != null ? exists : def;
	}

	public FST() {
		super();
		setAlwaysOnTop(true);
		setFocusableWindowState(false);
		setUndecorated(true);
                setFocusable(false);
                
                /*MouseAdapter m = new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        mouseP = InputEvent.getMaskForButton(e.getButton());//e.getButton();
                        System.out.println(InputEvent.BUTTON1_MASK);
                      
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        mouseR = InputEvent.getMaskForButton(e.getButton());
                        System.out.println(InputEvent.BUTTON1_MASK);
                    }
                };
                this.addMouseListener(m);*/
		new Thread(this).start();
	}

	@Override
	public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            //g.setColor(new Color(0, 0, 0, 0f));
            //g.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            g.setFont(font);
            g.setColor(fontColour);
            g.drawString(text, x, ascent);
	}

	int getPosition(int length, int width, int mode, int margin) {
		switch (mode) {
		case LEFT:
			return margin;
		case RIGHT:
			return length - width - margin;
		case CENTER:
			return (length - width) / 2;
		case RANDOM:
			return margin + (int) ((length - width - 2 * margin) * Math.random());
		default:
			return mode;
		}
	}

        @Override
	public void run() {
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			//Robot rbt = new Robot();
			Font tempFont = null;
			//FontMetrics fontMetrics = null;
			int height = 0;
			int messagePosition = -1;
                        Dimension screen = null;
                        setBackground(new Color(0f, 0f, 0f, 0f));
                        setVisible(true);
                        Graphics g = getGraphics();
                        setVisible(false);
                        //Long d = new Date().getTime();
			for (;;) {
                                /*Long now = new Date().getTime();
                                if (now > d + 60 * 1000) {
                                    System.gc();
                                    d = now;
                                }*/
                                boolean change = false;
				if (font != tempFont) {
					fontMetrics = g.getFontMetrics(font);
					height = fontMetrics.getHeight();
                                        ascent = fontMetrics.getAscent();
                                        change = true;
					tempFont = font;
				}
				Dimension scr = toolkit.getScreenSize();
                                if(!scr.equals(screen)) {
                                    change = true;
                                    screen = scr;
                                   
                                }
                                if(change) {
                                    setBounds(0,0,screen.width, height);
                                     System.out.print(scr.width);
                                }
                                
				if(messages.size() > 0) {
					text = orderMessage(messages.get(messageOrder == RANDOM ? (int) (Math.random() * messages.size()):
						(messagePosition = (messagePosition+1)%messages.size())));
					int width = fontMetrics.stringWidth(text);
					x = getPosition(screen.width, width, placementX, marginX);
					int y = getPosition(screen.height, height, placementY, marginY);
                                        setLocation(0, y);
                                        //setBounds(x, y, width*12/10, height); // 20% larger for macs (some strange fontMetrics)
                                        //setBackground(new Color(0f, 0f, 0f, 0f));
                                        //repaint();
                                        //this.paint
                                        
					setVisible(true);
					Thread.sleep(display);
					setVisible(false);
                                     /*   if(mouseP != 0 || mouseR != 0) {
                                            //System.out.println(mouseP+" "+mouseR);
                                            Robot r = new Robot();
                                            //r.mou
                                            if(mouseP != 0) r.mousePress(mouseP);
                                            if(mouseR != 0) r.mouseRelease(mouseR);
                                            mouseP = 0;
                                            mouseR = 0;
                                        }*/
                                        //System.gc();
				}
				Thread.sleep(delay);
				/*if (background != null) {
					background.flush();
				}*/
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
                        save();
			System.exit(1);
		}
	}
}
