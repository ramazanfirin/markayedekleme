package com.application.areca.launcher.gui.serialnumbercontrol;
import org.apache.commons.codec.binary.Base64;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Your first JFace application
 */
public class CreateSerialNumber extends ApplicationWindow {
  /**
   * HelloWorld constructor
   */
  public CreateSerialNumber() {
    super(null);
  }

  /**
   * Runs the application
   */
  public void run() {
    // Don't return from open() until window closes
    setBlockOnOpen(true);

    // Open the main window
    open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
      
	  final Shell shell = parent.getShell();
	  shell.setText("Seri Numarasi olusturma");
	  shell.setSize(500, 150);
	  
	  Monitor primary = shell.getDisplay().getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shell.getBounds();
	  int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	     shell.setLocation(x, y);
	  
	  Composite composite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout(1, false);
      layout.verticalSpacing = 10;
      composite.setLayout(layout);

      Group grpLocation=null;
      grpLocation = new Group(composite, SWT.NONE);
	    grpLocation.setText("Lisans Kodu Olusturma :");
	    grpLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	    GridLayout grpLayout = new GridLayout(3, false);
	    grpLayout.verticalSpacing = 0;
	    grpLocation.setLayout(grpLayout);
      
      
    Label label = new Label(grpLocation,SWT.NONE);
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
    label.setText("Lisans Bitis Tarihi(AY/GUN(YIL) : ");
    
//    Text serialNumberText = new Text(composite,SWT.NONE);
//    //serialNumberText.setText(serialNumber);
//    GridData mainData2 = new GridData(SWT.FILL, SWT.CENTER, true, false);
//    //mainData2.widthHint = computeWidth(200);
//    serialNumberText.setLayoutData(mainData2);         
	  
//    Composite composite = new Composite(parent, SWT.NONE);
//    composite.setLayout(new FillLayout());
    
     // calendar.get
    
    final DateTime calendar = new DateTime (grpLocation, SWT.DATE | SWT.MEDIUM );
    calendar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false ));
    calendar.addSelectionListener (new SelectionAdapter() {
        public void widgetSelected (SelectionEvent e) {
            System.out.println ("calendar date changed");
        }
    });
    
    Button searchDriverButton = new Button(grpLocation, SWT.NONE);
	searchDriverButton.setText("Lisans Kodu olustur");
	searchDriverButton.addSelectionListener(new SelectionListener() {
	      public void widgetSelected(SelectionEvent event) {
	    	  String date = calendar.getDay()+"/"+(calendar.getMonth()+1)+"/"+calendar.getYear();
	    	  System.out.println(date);
	    	  String licenceCode = new String(Base64.encodeBase64(date.getBytes()));
	    	  System.out.println(date+" "+licenceCode);
	    	  //MessageDialog.openWarning(shell, "Warning",licenceCode);
	      
	    	  final Shell dialog =
	              new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	            dialog.setLayout(new GridLayout(3, false));
	            
	            Monitor primary = shell.getDisplay().getPrimaryMonitor();
	    	    Rectangle bounds = primary.getBounds();
	    	    Rectangle rect = shell.getBounds();
	    	  int x = bounds.x + (bounds.width - rect.width) / 2;
	    	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    	    dialog.setLocation(x, y);
	            
	            
	            Label label = new Label(dialog,SWT.NONE);
	            label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
	            label.setText("Lisans kodu : ");
	      
	            Text text = new Text(dialog, SWT.NONE);
	            text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
	            text.setText(licenceCode);
	            text.setEditable(false);
	            
	            dialog.pack();
	            dialog.open();
	      }
	      public void widgetDefaultSelected(SelectionEvent event) {
	        //text.setText("No worries!");
	      }
	 });
    
	
	   Label label2 = new Label(grpLocation,SWT.NONE);
	    label2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
	    label2.setText("Lisans Kodu : ");    
	    
	    final Text text2 = new Text(grpLocation, SWT.NONE);
        text2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false ));
        
        Button searchDriverButton2 = new Button(grpLocation, SWT.NONE);
    	searchDriverButton2.setText("Bitis tarihi ogren      ");
    	searchDriverButton2.addSelectionListener(new SelectionListener() {
    	      public void widgetSelected(SelectionEvent event) {
    	    	     	      
    	    	  final Shell dialog =
    	              new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
    	            dialog.setLayout(new GridLayout(3, false));
    	            
    	            Monitor primary = shell.getDisplay().getPrimaryMonitor();
    	    	    Rectangle bounds = primary.getBounds();
    	    	    Rectangle rect = shell.getBounds();
    	    	    int x = bounds.x + (bounds.width - rect.width) / 2;
    	    	    int y = bounds.y + (bounds.height - rect.height) / 2;
    	    	    dialog.setLocation(x, y);
    	            
    	            Label label = new Label(dialog,SWT.NONE);
    	            label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
    	            label.setText("Lisans Bitis Tarihi(GUN/AY/YIL) : ");
    	      
    	            String date = new String(Base64.decodeBase64(text2.getText().getBytes()));
    	            
    	            Text text = new Text(dialog, SWT.NONE);
    	            text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
    	            text.setText(date);
    	            text.setEditable(false);
    	            
    	            dialog.pack();
    	            dialog.open();
    	      }
    	      public void widgetDefaultSelected(SelectionEvent event) {
    	        //text.setText("No worries!");
    	      }
    	 });
        
        
    return composite;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new CreateSerialNumber().run();
  }
}
