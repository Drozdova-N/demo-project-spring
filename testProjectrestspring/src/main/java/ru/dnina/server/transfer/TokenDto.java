package ru.dnina.server.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnina.server.models.Token;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {

    private String value;
    public static TokenDto from(Token token){
        return new TokenDto(token.getValue());
    }
}
