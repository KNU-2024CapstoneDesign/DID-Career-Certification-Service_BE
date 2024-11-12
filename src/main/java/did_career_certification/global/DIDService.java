package did_career_certification.global;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

@Service
public class DIDService {
    private final Web3j web3j;
    private final DIDRegistry didRegistry;

    public DIDService(Web3j web3j, DIDRegistry didRegistry) {
        this.web3j = web3j;
        this.didRegistry = didRegistry;
    }

    public void storeDID(String did, String subjectToken) throws Exception {
        // Call the `storeDIDDocument` function
        TransactionReceipt receipt = didRegistry.storeDIDDocument(did, subjectToken).send();
        System.out.println("Transaction successful: " + receipt.getTransactionHash());
    }

    public String getDIDOwner(String did) throws Exception {
        // Call the `getDIDDocument` function
        Tuple2<String, String> didData = didRegistry.getDIDDocument(did).send();
        return didData.getValue2(); // This returns the owner's address
    }
}
