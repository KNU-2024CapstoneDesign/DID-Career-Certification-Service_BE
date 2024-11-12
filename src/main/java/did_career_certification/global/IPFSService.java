package did_career_certification.global;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Service;

@Service
public class IPFSService {

    private final IPFS ipfs;

    public IPFSService() {
        // 로컬 IPFS 노드에 연결
        this.ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001"); // 로컬에서 실행하는 경우
    }

    public String saveCertificate(String subjectToken) throws IOException {
        // Convert the StringToken to InputStream
        InputStream inputStream = new ByteArrayInputStream(subjectToken.getBytes());

        // Upload the string to IPFS
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(inputStream.readAllBytes());
        MerkleNode addResult = ipfs.add(file).get(0);

        // Return the IPFS hash of the file
        return addResult.hash.toString();
    }
}
