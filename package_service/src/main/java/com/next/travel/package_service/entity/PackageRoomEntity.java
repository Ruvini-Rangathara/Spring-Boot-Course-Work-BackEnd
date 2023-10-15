package com.next.travel.package_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageRoomEntity {

    @Id
    private long id;

    private String packageId;

    private String roomId;
}
