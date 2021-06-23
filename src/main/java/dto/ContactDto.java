package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ContactDto {
    long id;
    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;
}
