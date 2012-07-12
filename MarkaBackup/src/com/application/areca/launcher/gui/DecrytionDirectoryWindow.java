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
public class DecrytionDirectoryWindow 
extends DecrytionFileWindow {
    private static final ResourceManager RM = ResourceManager.instance();
    
   

    public DecrytionDirectoryWindow() {
    	super();
    	isFolder = Boolean.TRUE;
    
    }
    
   
    
    public void closeInError(Exception e) {
    	SecuredRunner.execute(new Runnable() {
			public void run() {
		        application.disableWaitCursor(DecrytionDirectoryWindow.this);
		        DecrytionDirectoryWindow.this.close();
	    	}
    	});
    }
    
    
    
    public String getTitle() {
        return RM.getLabel("check.dialog.title") + " (" +"decnryt Folder" + ")";
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

    

    protected void updateState(boolean rulesSatisfied) {
//        this.btnSave.setEnabled(rulesSatisfied);
    }

	public boolean close() {
		boolean closed = super.close();
		
		return closed;
	}
	
	
	
	
}
