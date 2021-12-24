package barcode;

import barcode.subsystem.BarcodeSubsystemController;

public class BarcodeProcessor implements BarcodeInterface {

    private BarcodeSubsystemController ctrl;
    public BarcodeProcessor(){
        this.ctrl = new BarcodeSubsystemController();
    }

    @Override
    public int processBarcode(String barcode) {
        return this.ctrl.convertBikeID(barcode);
    }
}
