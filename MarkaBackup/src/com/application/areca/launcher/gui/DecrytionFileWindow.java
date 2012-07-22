package com.application.areca.launcher.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.application.areca.ResourceManager;
import com.application.areca.external.CmdLineDeCipherInternal;
import com.application.areca.launcher.gui.common.AbstractWindow;
import com.application.areca.launcher.gui.common.Colors;
import com.application.areca.launcher.gui.common.SavePanel;
import com.application.areca.launcher.gui.common.SecuredRunner;

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
    
    Combo combo;
    
    Button btnSave;
    
    
    Boolean isFolder;
    
    String YES="EVET";
    String NO="HAYIR";
    
    StyledText txtLog;
    Text txtMessage;
    
    private Application.ProcessRunner runner;

    public DecrytionFileWindow() {
    	super();
    	isFolder = Boolean.FALSE;
    
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
        //textFile.setText("D:/backup_deneme/yedek/790396242/321b3a446dd86ade628911f0589d2882");
        
        buttonBrowseFile = new Button(grpLocation, SWT.PUSH);
        buttonBrowseFile.setText(RM.getLabel("common.browseaction.label"));
        buttonBrowseFile.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
            	String path="";
            	if(Boolean.FALSE==isFolder)
            		path = Application.getInstance().showFileDialog(textFile.getText(), DecrytionFileWindow.this);
            	else
            		path = Application.getInstance().showDirectoryDialog(textFile.getText(),DecrytionFileWindow.this);
            	
            	if (path != null) {
                	textFile.setText(path);
                }
            }
        });
        buttonBrowseFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));


        Label labelAlgorithm = new Label(grpLocation, SWT.NONE);
        labelAlgorithm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
        labelAlgorithm.setText("Algoritma");

        textAlgorithm = new Text(grpLocation, SWT.READ_ONLY | SWT.BORDER);
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
        //textPassword.setText("ramazanfirin");
        new Label(grpLocation, SWT.NONE);
        
        Label labelFileNameEnc = new Label(grpLocation, SWT.NONE);
        labelFileNameEnc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
        labelFileNameEnc.setText("Dosya Isimleri Sifrele mi?");

        combo = new Combo(grpLocation, SWT.NONE);
        combo.add(YES);
        combo.add(NO);
        combo.select(0);
        new Label(grpLocation, SWT.NONE);
        
        Label labelOutputDirectory = new Label(grpLocation, SWT.NONE);
        labelOutputDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false ));
        labelOutputDirectory.setText("Output");

        textOutputDirectory = new Text(grpLocation, SWT.BORDER);
        GridData mainData5 = new GridData(SWT.FILL, SWT.CENTER, true, false);
        mainData5.widthHint = computeWidth(200);
        textOutputDirectory.setLayoutData(mainData2);       
        //textOutputDirectory.setText("c:/areca_yedek/3");
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
        
        SavePanel pnlSave = new SavePanel(RM.getLabel("common.save.label"), this);
        pnlSave.setShowCancel(false);
        pnlSave.buildComposite(composite).setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));        
        btnSave = pnlSave.getBtnSave();
        
        
//        final Group grpLocation2 = new Group(composite, SWT.NONE);
//        grpLocation2.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true));
//        GridLayout grpLayout2 = new GridLayout(3, false);
//        grpLayout.verticalSpacing = 0;
//        grpLocation2.setLayout(grpLayout2);
//        grpLocation.setText(RM.getLabel("check.location.label"));
        
//        txtLog = new StyledText(grpLocation2, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		//txtLog.setForeground(Colors.C_GREY);
//        txtLog.append("deneme");
//        txtLog.setSize(200,300);
        
        
//        txtMessage = new Text(grpLocation2, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
//		GridData dt = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
//		dt.heightHint = AbstractWindow.computeHeight(250);
//		dt.widthHint = AbstractWindow.computeWidth(200);
//		txtMessage.setLayoutData(dt);
        
//        GridData dt1 = new GridData();
//		dt1.grabExcessHorizontalSpace = true;
//		dt1.grabExcessVerticalSpace = true;
//		dt1.horizontalAlignment = SWT.FILL;
//		dt1.verticalAlignment = SWT.FILL;
//		txtLog.setLayoutData(dt1);
        
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
        
    	Thread updateThread = decrpyt(textFile.getText(), textAlgorithm.getText(), textPassword.getText(), textOutputDirectory.getText(),combo.getText() ,txtMessage,getShell());
  	  BusyIndicator.showWhile(getShell().getDisplay(), updateThread);
  	  System.out.println("update tamam");
    }

    protected void updateState(boolean rulesSatisfied) {
//        this.btnSave.setEnabled(rulesSatisfied);
    }

	public boolean close() {
		this.hasBeenUpdated=false;
		boolean closed = super.close();
		
		//boolean closed = false;
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
			 String _fileNameEnc,
			 Text _txtLog,
			 Shell _shell) {
		
		final String source=_source;
		final String algorithm=_algorithm;
		final String password=_password;
		final String destination=_destination;
		final String fileNameEnc=_fileNameEnc;
		final Text txtLog=_txtLog;
		;
		final Shell shell = _shell;
	    
	    
       return new Thread() {
	      public void run() {
	    	  final StringBuffer message=new StringBuffer(); 
	    	  
	        try {
	        	
	        	//Logger.defaultLogger().info("Looking for decrpyt for..." +source+" ,destination="+destination);         
				//target.processBackup(manifest, backupScheme, disablePreCheck, checkParams, transactionPoint, context);
	        	String dir = System.getProperty("user.dir");
	        	
				String[] array=new String[5];
				array[0] = "-source="+source;
				array[1] = "-algorithm="+algorithm;
				array[2] = "-password="+password;
				array[3] = "-destination="+destination;
				if(fileNameEnc.equals(NO))
					array[4] = "-r";
				else
					array[4] ="";
				
				String queryString = "-source="+source +" "+"-algorithm="+algorithm+" "+"-password="+password+" "+"-destination="+destination;
				if(fileNameEnc.equals(NO))
					queryString = queryString+" -r";
				
				
				CmdLineDeCipherInternal.getInstance().decryt(array);
				
				
	
		     
				
				
	        	message.append("Sifre acma islemi tamamladi.\nDosyanin kaydedildigi yer = "+destination);
	        } catch (Exception e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	          message.append("hata: "+e.getMessage());
	        }

	        shell.getDisplay().asyncExec(new Runnable() {
	          public void run() {
	        	 try{
//	        		 String dir = System.getProperty("user.dir"); 
//	        		
//	        		 File url=new File(dir, "decrypt.exe");
//	        		 MessageDialog.openWarning(shell, "Warning", url.getAbsolutePath());
//	        		 Process p = Runtime.getRuntime().exec(url.getAbsolutePath());
//					
//					
//					BufferedReader bri = new BufferedReader
//			        (new InputStreamReader(p.getInputStream()));
//			      BufferedReader bre = new BufferedReader
//			        (new InputStreamReader(p.getErrorStream()));
//	        	  
//	        	  
//	        	  
//	        	  String line;
//	        	  //MessageDialog.openWarning(shell, "Warning", message+"---");
//	    	      while ((line = bri.readLine()) != null) {
//	  		        txtLog.append(line+"\n");
//	  		      }
//	  		      bri.close();
//	  		      while ((line = bre.readLine()) != null) {
//	  		    	  txtLog.append(line+"\n");
//	  		      }
//	  		      
//	  		    bre.close();
//			      p.waitFor();
	        		 
	        		 MessageDialog.openWarning(shell, "Warning",message.toString());
	        	 }catch(Exception e){
	        		 MessageDialog.openWarning(shell, "Warning", e.getMessage());
	        	 }
	        	 
	        	 
	        	 }
	        });
	      }
	    };
	}
	
	
}
