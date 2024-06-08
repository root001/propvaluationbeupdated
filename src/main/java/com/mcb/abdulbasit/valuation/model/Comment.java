package com.mcb.abdulbasit.valuation.model;

import com.mcb.abdulbasit.valuation.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    @NonNull
    private String body;

}
