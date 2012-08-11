package com.application.areca.launcher.gui.serialnumbercontrol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.application.areca.ResourceManager;
import com.application.areca.Utils;

/**
 * Your first JFace application
 */
public class SerialNumberWindow extends ApplicationWindow {
  /**
   * HelloWorld constructor
   */

  Text serialNumberText;	
  Label warning;
  String serialNumber;	
  
  boolean licenceExpired = false;
  boolean last30Days= false;
  
  Button buttonOk;
  Button buttonCancel;
  
  Date expireDate= new Date();
  
  static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  
  public SerialNumberWindow(String _serialNumber) {
    super(null);
    serialNumber = _serialNumber;
    checkSerialNumber();
    

  }
  
  public SerialNumberWindow(String _serialNumber,Shell shell) {
	    super(null);
	    serialNumber = _serialNumber;
	    checkSerialNumber();
	    setParentShell(shell);
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
    //Display.getCurrent().dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  public void checkSerialNumber(){
	  Date date = Utils.serialNumberToDate(serialNumber);
	  if(date!=null){
		  try {
			  licenceExpired  = Utils.isSerialNumberExpired(serialNumber);
			  last30Days = Utils.inLast30Days(serialNumber);
			  expireDate = Utils.serialNumberToDate(serialNumber);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }
  
  protected Control createContents(Composite parent) {
    // Create a Hello, World label
	  getShell().setText("Seri No");
	  
	  getShell().addListener(SWT.Close, new Listener() {
	      public void handleEvent(Event event) {
	          System.out.println("cose");
	        }
	   });
	  
		  
	  if(licenceExpired)
		  MessageDialog.openWarning(parent.getShell(), "Warning",ResourceManager.instance().getLabel("serial.number.expire"));
	  else if(last30Days)
		  MessageDialog.openWarning(parent.getShell(), "Warning",ResourceManager.instance().getLabel("serial.number.last30Days",new Object[] {formatter.format(expireDate)}));
	  
   
	  Composite composite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout(2, false);
      layout.verticalSpacing = 10;
      composite.setLayout(layout);

    Label label = new Label(composite,SWT.NONE);
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
    label.setText("Seri No:");
    
    serialNumberText = new Text(composite,SWT.NONE);
    serialNumberText.setText(serialNumber);
    GridData mainData2 = new GridData(SWT.FILL, SWT.CENTER, true, false);
    //mainData2.widthHint = computeWidth(200);
    serialNumberText.setLayoutData(mainData2);       
    
    buttonOk = new Button(composite, SWT.PUSH);
    buttonOk.setText(ResourceManager.instance().getLabel("common.save.label"));
    buttonOk.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event arg0) {
			
				try {
					if(Utils.checkSerialNumber(serialNumberText.getText())){
						Utils.updateSerialNumber(serialNumberText.getText());
						//getShell().close();
						close();
					}else{
						MessageDialog.openWarning(getShell(), "Warning",ResourceManager.instance().getLabel("serial.number.invalid"));
				}
				} catch (Exception e) {
					e.printStackTrace();
					MessageDialog.openWarning(getShell(), "Error","Hata="+e.getMessage());
				}
			
			
		}
    });
    buttonOk.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    
    
    buttonCancel = new Button(composite, SWT.PUSH);
    buttonCancel.setText(ResourceManager.instance().getLabel("common.cancel.label"));
    buttonCancel.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event arg0) {
			getShell().close();
        }
    });
    buttonCancel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    
    return composite;
  }
  
//  public void checkSerialNumber(Shell shell,String serialNumber){
//	  if(1==1)
//		  MessageDialog.openWarning(shell, "Warning","Lisans süreniz bitmistir.\nYeni linans numarasi giriniz");
//  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new SerialNumberWindow("").run();
  }

public boolean isLicenceExpired() {
	return licenceExpired;
}

public void setLicenceExpired(boolean licenceExpired) {
	this.licenceExpired = licenceExpired;
}

public boolean isLast30Days() {
	return last30Days;
}

public void setLast30Days(boolean last30Days) {
	this.last30Days = last30Days;
}


public static String encrypt(String string){
	byte[] encoded = Base64.encodeBase64(string.getBytes()); 
	return new String(encoded);
}

public static String dencrypt(String string){
	byte[] encoded = Base64.decodeBase64(string.getBytes()); 
	return new String(encoded);
}




}