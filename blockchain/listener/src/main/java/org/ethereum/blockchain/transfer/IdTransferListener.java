package org.ethereum.blockchain.transfer;

public interface IdTransferListener {
    void onTransfer(Transfer transfer, int confirmations, boolean confirmed);
}
