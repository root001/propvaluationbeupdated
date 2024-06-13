package com.mcb.abdulbasit.valuation.model;

import com.mcb.abdulbasit.valuation.entity.BaseEntity;
import com.mcb.abdulbasit.valuation.enums.DocType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity {
    @NonNull
    @Column
    @Enumerated(EnumType.STRING)
    private DocType docType;
    @NonNull
    private String name;
    @NonNull
    private String filePath;
    private String extension;
    private Long size;
}
