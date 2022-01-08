package barcode.exception;

public class InvalidBarcodeException extends BarcodeException {
    public InvalidBarcodeException() {
        super("ERROR: Invalid barcode!");
    }
}
