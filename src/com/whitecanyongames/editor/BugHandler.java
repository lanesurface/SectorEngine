//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.editor;

import java.awt.Color;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class BugHandler {
	public static final int SEV_INFO    = 0b0001,
							SEV_WARNING = 0b0010,
							SEV_ERROR   = 0b0100;
	
	public void appendToConsole(String msg, int flag) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        
        Color c = Color.BLACK;
        if ((flag & SEV_INFO) == SEV_INFO) c = Color.BLUE;
        else if ((flag & SEV_WARNING) == SEV_WARNING) c = Color.ORANGE;
        else if ((flag & SEV_ERROR) == SEV_ERROR) c = Color.RED;
        
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = Editor.consoleTextPane.getDocument().getLength();
        Editor.consoleTextPane.setCaretPosition(len);
        Editor.consoleTextPane.setCharacterAttributes(aset, false);
        Editor.consoleTextPane.replaceSelection(">> " + msg + '\n');
    }
}
