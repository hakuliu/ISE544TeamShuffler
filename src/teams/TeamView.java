package teams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;

import global.UtilController;
import member.Member;
import member.MemberCellRenderer;
import member.MemberTransferListener;

public class TeamView {
	JPanel panel;
	TeamModel tmodel;
	TeamQuickStatView bottomview;
	public TeamView(TeamModel mod, UtilController controller) {
		this.tmodel = mod;
		this.panel = new JPanel();
		this.bottomview = new TeamQuickStatView(this.tmodel, controller);
	}
	
	public JPanel build() {
		this.panel.setLayout(new BorderLayout());
		this.panel.setBackground(Color.gray);
		this.panel.add(new JLabel(tmodel.teamName), BorderLayout.NORTH);
		//this guy's created empty for now.
		JList<Member> list = getList();
		JScrollPane scrolly = new JScrollPane(list);
		this.panel.add(scrolly, BorderLayout.CENTER);
		
		this.panel.add(this.bottomview.build(), BorderLayout.SOUTH);
		return this.panel;
	}
	
	private JList<Member> getList() {
		JList<Member> list = new JList<Member>();
		list.setModel(this.tmodel.getMemberListModel());
		list.setVisibleRowCount(5);
		list.setFixedCellHeight(15);
		list.setFixedCellWidth(100);
		list.setCellRenderer(new MemberCellRenderer());
		list.setDragEnabled(true);
		list.setTransferHandler(new MemberTransferListener());
		list.setDropMode(DropMode.ON_OR_INSERT);
		return list;
	}
}
