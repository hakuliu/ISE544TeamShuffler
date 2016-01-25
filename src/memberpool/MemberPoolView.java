package memberpool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.TransferHandler;

import member.Member;
import member.MemberCellRenderer;
import member.MemberTransferListener;

public class MemberPoolView {
	private MemberPoolModel model;
	private JPanel panel;
	public MemberPoolView(MemberPoolModel model) {
		this.model = model;
		this.panel = new JPanel(new BorderLayout());

	}
	
	public JPanel buildView() {
		this.panel.removeAll();
		
		JList<Member> freememberslist = new JList<>(this.model.getModel());
		freememberslist.setVisibleRowCount(10);
		freememberslist.setFixedCellHeight(25);
		freememberslist.setFixedCellWidth(100);
		freememberslist.setCellRenderer(new MemberCellRenderer());
		freememberslist.setDragEnabled(true);
		freememberslist.setTransferHandler(new MemberTransferListener());
		//freememberslist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		freememberslist.setDropMode(DropMode.ON_OR_INSERT);
		JScrollPane scrolly = new JScrollPane(freememberslist);
		
		this.panel.add(scrolly, BorderLayout.CENTER);
		
		return this.panel;
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
}
