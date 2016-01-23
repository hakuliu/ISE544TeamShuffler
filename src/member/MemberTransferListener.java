package member;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.TransferHandler;

public class MemberTransferListener extends TransferHandler {
	private static final long serialVersionUID = -2205008650342892842L;
	@Override
    public boolean canImport(TransferSupport support) {
        return (support.getComponent() instanceof JList<?>) && support.isDataFlavorSupported(MemberTransferable.MEMBER_DATA_FLAVOR);
    }

    @Override
    public boolean importData(TransferSupport support) {
        boolean accept = false;
        if (canImport(support)) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(MemberTransferable.MEMBER_DATA_FLAVOR);
                if (value instanceof Member) {
                    Component component = support.getComponent();
                    if (component instanceof JList) {
                    	Member val = ((Member)value);
                        JList<Member> droplist = ((JList<Member>)component);
                        ListModel<Member> mod = droplist.getModel();
                        DefaultListModel<Member> dlm = (DefaultListModel<Member>)mod;
                        dlm.addElement(val);
                        accept = true;
                    }
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        return accept;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_MOVE;
        
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = null;
        if (c instanceof JList) {
            JList<Member> list = (JList<Member>) c;
            Member value = list.getSelectedValue();
            t = new MemberTransferable(value);
        }
        return t;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
    	JList<Member> droplist = ((JList<Member>)source);
    	
    	ListModel<Member> mm = droplist.getModel();
    	DefaultListModel<Member> dlm = (DefaultListModel<Member>)mm;
    	dlm.remove(droplist.getSelectedIndex());

    }
}
