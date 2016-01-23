package member;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class MemberTransferable implements Transferable {

    public static final DataFlavor MEMBER_DATA_FLAVOR = new DataFlavor(Member.class, "java/Member");
    private Member member;

    public MemberTransferable(Member mem) {
		this.member = mem;
	}

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{MEMBER_DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(MEMBER_DATA_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

        return member;

    }

}
