package jpabook.jpashop.validation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class BookSaveForm {
    @NotBlank(message = "상품명을 입력해주세요")
    private String name;
    @NotNull
    @Min(value = 0, message = "가격은 0원 이상으로 입력해주세요")
    private int price;
    @NotNull(message = "수량을 입력해주세요")
    @Max(value = 9999, message = "수량은 9999이하로 입력해주세요")
    @Min(value = 1, message = "수량은 1개 이상으로 입력해주세요")
    private int stockQuantity;

    @NotBlank(message = "저자를 입력해주세요")
    private String author;
    @NotBlank(message = "ISBN을 입력해주세요")
    private String isbn;
}
