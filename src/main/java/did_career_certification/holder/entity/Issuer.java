package did_career_certification.holder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Entity
@Getter
public class Issuer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String requestApi;

    @Column(nullable = false)
    private String did;

    private String requireData;

    public Map<String, Object> toDto() {
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("name", name);
        List<String> req = new ArrayList<>();
        req.add(requireData);
        response.put("requireData", req);
        return response;
    }
}
