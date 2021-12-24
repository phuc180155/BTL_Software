package barcode;

/**
 * Interface processes barcodes
 */
public interface BarcodeInterface {
    /**
     * Turn barcode to int code
     * @param barcode barcode of bike
     * @return bike id
     */
    int processBarcode(String barcode);
}
