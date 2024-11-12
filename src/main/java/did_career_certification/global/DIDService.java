package did_career_certification.global;

import static did_career_certification.config.Web3jConfig.CONTRACT_ADDRESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class DIDService {

    @Autowired
    private Web3j web3j;  // Inject Web3j instance

    @Autowired
    private Credentials credentials;

    public String storeDID(String did, String subjectToken) throws Exception {
        // 스마트 계약 로드
        DIDRegistry contract = DIDRegistry.load(CONTRACT_ADDRESS, web3j, credentials, new DefaultGasProvider());

        // DID와 subjectToken을 저장하는 트랜잭션 생성
        TransactionReceipt receipt = contract.storeDIDDocument(did, subjectToken).send();

        System.out.println("Transaction successful with hash: " + receipt.getTransactionHash());
        // 트랜잭션 해시 반환
        return receipt.getTransactionHash();
    }

    public String getDID(String txHash) throws Exception {
        // Web3j를 사용하여 트랜잭션 정보를 조회합니다.
        TransactionReceipt receipt = web3j.ethGetTransactionReceipt(txHash).send().getResult();

        if (receipt == null) {
            throw new RuntimeException("Transaction not found or not mined yet");
        }

        // 트랜잭션이 성공적이라면, 해당 트랜잭션을 통해 DID를 조회
        DIDRegistry contract = DIDRegistry.load(CONTRACT_ADDRESS, web3j, credentials, new DefaultGasProvider());

        // 트랜잭션 해시값을 사용하여 DIDDocument를 조회
        String subjectToken = contract.getDIDDocumentByTxHash(txHash.getBytes()).send().getValue1();

        System.out.println("Subject Token: " + subjectToken);
        return subjectToken;
    }
}
