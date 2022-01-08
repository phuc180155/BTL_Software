package barcode;

import barcode.exception.BarcodeException;
import barcode.exception.InvalidBarcodeException;
import barcode.subsystem.BarcodeSubsystemController;

import java.net.MalformedURLException;

public class BarcodeProcessor implements BarcodeInterface {

    private BarcodeSubsystemController ctrl;
    public BarcodeProcessor(){
        this.ctrl = new BarcodeSubsystemController();
    }

    @Override
    public int processBarcode(String barcode) throws MalformedURLException, BarcodeException {
        return this.ctrl.processBarcode(barcode);
    }
}
