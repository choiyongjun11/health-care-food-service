package com.healthcare.response;

import com.healthcare.utils.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import javax.persistence.Access;
import java.util.List;

@Getter
@AllArgsConstructor
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page<?> page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber(),
                                    page.getSize(),
                                    page.getTotalPages());
    }

}
