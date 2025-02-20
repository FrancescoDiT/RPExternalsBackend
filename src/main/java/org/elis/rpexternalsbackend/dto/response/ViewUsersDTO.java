package org.elis.rpexternalsbackend.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.rpexternalsbackend.model.UserType;

@Data
@AllArgsConstructor
public class ViewUsersDTO {
    String name;
    String surname;
    String email;
    String password;
    UserType type;
}
