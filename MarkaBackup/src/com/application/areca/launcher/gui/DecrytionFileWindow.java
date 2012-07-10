package com.application.areca.launcher.gui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.application.areca.ResourceManager;
import com.application.areca.external.CmdLineDeCipher;
import com.application.areca.external.CmdLineDeCipherInternal;
import com.application.areca.launcher.gui.common.AbstractWindow;
import com.application.areca.launcher.gui.common.SavePanel;
import com.application.areca.launcher.gui.common.SecuredRunner;
import com.myJava.util.log.Logger;

/**
 * <BR>
 * @author Olivier PETRUCCI
 * <BR>
 *
 */

 /*
 Copyright 2005-2011, Olivier PETRUCCI.

This file is part of Areca.

    Areca is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    Areca is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Areca; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

 */
public class DecrytionFileWindow 
extends AbstractWindow {
    private static final ResourceManager RM = ResourceManager.instance();
    
    Label labelFile;
    Text textFile;
    Button buttonBrowseFile;
    
    Label labelAlgorithm;
    Text textAlgorithm;
  
    Label labelPassword;
    Text textPassword;
    
    Label labelOutputDirectory;
    Text textOutputDirectory;
    Button buttonBrowseOutputDirectory;
    
    Button btnSave;
    
     private Application.ProcessRunner runner;

    public DecrytionFileWindow() {
    	super();
    
    }
    
    protected Control createContents(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        layout.verticalSpacing = 10;
        composite.setLayout(layout);

        final Group grpLocation = new Group(composite, SWT.NONE);
        grpLocation.setText(RM.getLabel("check.location.label"));
        grpLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        GridLayout grpLayout = new GridLayout(3, false);
        grpLayout.verticalSpacing = 0;
        grpLocation.setLayout(grpLayout);
        
        Label labelFile = new Label(grpLocation, SWT.NONE);
        labelFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
        labelFile.setText("Dosya/Dizin");

        textFile = new Text(grpLocation, SWT.BORDER);
        GridData mainData2 = new GridData(SWT.FILL, SWT.CENTER, true, false);
        mainData2.widthHint = computeWidth(200);
        textFile.setLayoutData(mainData2);       
        monitorControl(textFile);
        textFile.setText("D:/backup_deneme/yedek/790396242/321b3a446dd86ade628911f0589d2882");
        
        buttonBrowseFile = new Button(grpLocation, SWT.PUSH);
        buttonBrowseFile.setText(RM.getLabel("common.browseaction.label"));
        buttonBrowseFile.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                String path = Application.getInstance().showFileDialog(textFile.getText(), DecrytionFileWindow.this);
                if (path != null) {
                	textFile.setText(path);
                }
            }
        });
        buttonBrowseFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));


        Label labelAlgorithm = new Label(grpLocation, SWT.NONE);
        labelAlgorithm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
        labelAlgorithm.setText("Algoritma");

        textAlgorithm = new Text(grpLocation, SWT.BORDER);
        GridData mainData3 = new GridData(SWT.FILL, SWT.CENTER, true, false);
        mainData3.widthHint = computeWidth(200);
        textAlgorithm.setLayoutData(mainData3);       
        monitorControl(textAlgorithm);
        textAlgorithm.setText("AES_HASH");
        new Label(grpLocation, SWT.NONE);
        
        Label labelPassword = new Label(grpLocation, SWT.NONE);
        labelPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
        labelPassword.setText("Sifre");

        textPassword= new Text(grpLocation, SWT.BORDER);
        GridData mainData4 = new GridData(SWT.FILL, SWT.CENTER, true, false);
        mainData4.widthHint = computeWidth(200);
        textPassword.setLayoutData(mainData4);       
        monitorControl(textPassword);
        textPassword.setText("ramazanfirin");
        new Label(grpLocation, SWT.NONE);
        
        
        
        
        
        Label labelOutputDirectory = new Label(grpLocation, SWT.NONE);
        labelOutputDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
        labelOutputDirectory.setText("Output");

        textOutputDirectory = new Text(grpLocation, SWT.BORDER);
        GridData mainData5 = new GridData(SWT.FILL, SWT.CENTER, true, false);
        mainData5.widthHint = computeWidth(200);
        textOutputDirectory.setLayoutData(mainData2);       
        textOutputDirectory.setText("c:/areca_yedek/3");
        monitorControl(textOutputDirectory);
        
        
        buttonBrowseOutputDirectory = new Button(grpLocation, SWT.PUSH);
        buttonBrowseOutputDirectory.setText(RM.getLabel("common.browseaction.label"));
        buttonBrowseOutputDirectory.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                String path = Application.getInstance().showDirectoryDialog(textFile.getText(), DecrytionFileWindow.this);
                if (path != null) {
                	textOutputDirectory.setText(path);
                }
            }
        });
        buttonBrowseOutputDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        
        SavePanel pnlSave = new SavePanel(RM.getLabel("check.check.label"), this);
        pnlSave.setShowCancel(false);
        pnlSave.buildComposite(composite).setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));        
        btnSave = pnlSave.getBtnSave();
        
       
        composite.pack();
        return composite;
    }
    
    public void closeInError(Exception e) {
    	SecuredRunner.execute(new Runnable() {
			public void run() {
		        application.disableWaitCursor(DecrytionFileWindow.this);
		        DecrytionFileWindow.this.close();
	    	}
    	});
    }
    
    
    
    public String getTitle() {
        return RM.getLabel("check.dialog.title") + " (" +"decnryt" + ")";
    }

	protected boolean checkBusinessRules() {
//        this.resetErrorState(txtLocation); 
//        if (radUseSpecificLocation.getSelection()) {
//	        if (this.txtLocation.getText() == null || this.txtLocation.getText().length() == 0) {
//	            this.setInError(txtLocation, RM.getLabel("error.field.mandatory"));
//	            return false;
//	        }
//        }
        return true;
    }

    protected void saveChanges() { 
		
        //this.runner = application.launchDecrpyt(textFile.getText(), textAlgorithm.getText(), textPassword.getText(), textOutputDirectory.getText());
        
    	Thread updateThread = decrpyt(textFile.getText(), textAlgorithm.getText(), textPassword.getText(), textOutputDirectory.getText(),getShell());
  	  BusyIndicator.showWhile(getShell().getDisplay(), updateThread);
  	  System.out.println("update tamam");
    }

    protected void updateState(boolean rulesSatisfied) {
//        this.btnSave.setEnabled(rulesSatisfied);
    }

	public boolean close() {
		boolean closed = super.close();
		if (closed && runner != null && runner.getChannel() != null && runner.getChannel().isRunning() && runner.getChannel().getTaskMonitor() != null) {
			// Cancel the current task
			runner.getChannel().getTaskMonitor().setCancelRequested();
		}
		return closed;
	}
	
	public Thread decrpyt(String _source,
			 String _algorithm,
			 String _password,
			 String _destination,
			 Shell _shell) {
		
		final String source=_source;
		final String algorithm=_algorithm;
		final String password=_password;
		final String destination=_destination;
		
		final Shell shell = _shell;
	    
	    
       return new Thread() {
	      public void run() {
	    	  final StringBuffer message=new StringBuffer(); 
	    	  
	        try {
	        	
	        	//Logger.defaultLogger().info("Looking for decrpyt for..." +source+" ,destination="+destination);         
				//target.processBackup(manifest, backupScheme, disablePreCheck, checkParams, transactionPoint, context);
			
				String[] array=new String[4];
				array[0] = "-source="+source;
				array[1] = "-algorithm="+algorithm;
				array[2] = "-password="+password;
				array[3] = "-destination="+destination;
				CmdLineDeCipherInternal.main(array);
	        	
	        	message.append("Update tamamlandi");
	        } catch (Exception e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	          message.append("hata olustu");
	        }

	        shell.getDisplay().asyncExec(new Runnable() {
	          public void run() {
	        	  MessageDialog.openWarning(shell, "Warning", message+"---");
	              shell.close();
	          }
	        });
	      }
	    };
	}
	
	
}
