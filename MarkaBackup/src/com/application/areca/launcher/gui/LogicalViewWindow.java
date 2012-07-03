package com.application.areca.launcher.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.application.areca.ArecaURLs;
import com.application.areca.ResourceManager;
import com.application.areca.launcher.gui.common.AbstractWindow;
import com.application.areca.launcher.gui.common.ArecaImages;
import com.application.areca.launcher.gui.composites.ArchivesViewComposite;
import com.application.areca.launcher.gui.composites.LogicalViewComposite;
import com.application.areca.launcher.gui.composites.PhysicalViewComposite;
import com.application.areca.launcher.gui.composites.PropertiesComposite;
import com.application.areca.version.VersionInfos;
import com.myJava.system.OSTool;

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
public class LogicalViewWindow
extends AbstractWindow
implements ArecaURLs {
	private static final int CURRENT_YEAR = VersionInfos.getLastVersion().getYear();
    private static final int widthHint = computeWidth(2000);
    private static final int heightHint = computeHeight(1000);
    
    private static final ResourceManager RM = ResourceManager.instance();
    private static final String CREDITS_TXT_WIN = RM.getLabel("about.creditswin2.label", new Object[] {VersionInfos.APP_NAME});

    private PropertiesComposite pnlProperties;
    
    protected Control createContents(Composite parent) {
        application.enableWaitCursor();
        LogicalViewComposite ret = new LogicalViewComposite(parent);
        try {
           // GridLayout layout = new GridLayout(2, false);
           // ret.setLayout(layout);
            ret.refresh();
            
         // PROPERTIES
           // pnlProperties = new PropertiesComposite(ret);
            
            

        } finally {
            application.disableWaitCursor();
        }
        return ret;
    }
    
   
    public static int computeWidth(int linuxW) {
		if (OSTool.isSystemWindows()) {
			return (int)(linuxW * 0.8);
		} else {
			return linuxW;
		}
	}

	public static int computeHeight(int linuxH) {
		if (OSTool.isSystemWindows()) {
			return (int)(linuxH * 0.8);
		} else {
			return linuxH;
		}
	}
    
	protected void configureShell(final Shell shell) {
		super.configureShell(shell);

		size= computeSize(widthHint, heightHint);
		if (size != null) {
			shell.setSize(size);
		}
		shell.setText(getFullWindowTitle());
		shell.setImage(ArecaImages.ICO_SMALL);
	}
	


    protected boolean checkBusinessRules() {
        return true;
    }

    public String getTitle() {
        return ResourceManager.instance().getLabel("targetproperies.dialog.title");
    }

    protected void saveChanges() {
    }

    protected void updateState(boolean rulesSatisfied) {
    }
}
