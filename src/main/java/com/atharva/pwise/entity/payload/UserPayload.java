package com.atharva.pwise.entity.payload;

import com.atharva.pwise.entity.BankAccountInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserPayload {
    private String firstName;
    private String lastName;
    private String phone;
    private Long budget;
    private List<BankAccountInfo> bankAccountInfoList;
}
