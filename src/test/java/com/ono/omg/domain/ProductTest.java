package com.ono.omg.domain;

import com.ono.omg.dto.request.ProductReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("상품의")
class ProductTest {

    @Nested
    @DisplayName("Create 메서드는")
    class Create {
        @Test
        @DisplayName("상품에는 판매자 정보와 상품 관련 필드가 생성된다.")
        public void 상품_등록() throws Exception {
            // given
            Product product = new Product("computer", 1000000, "컴퓨터", "초고속 배송", 100, 2L);

            // when & then
            assertThat(product.getTitle()).isEqualTo("computer");
            assertThat(product.getPrice()).isEqualTo(1000000);
            assertThat(product.getImgUrl().equals("")).isFalse();
            assertThat(product.getCategory()).isEqualTo("컴퓨터");
            assertThat(product.getDelivery()).isEqualTo("초고속 배송");
            assertThat(product.getStock()).isEqualTo(100);
            assertThat(product.getSellerId()).isEqualTo(2L);
        }
    }

    @Nested
    @DisplayName("Update 메서드는")
    class Update {
        @Test
        @DisplayName("상품이 판매되면 재고가 한 개씩 깎인다.")
        public void 상품_판매() throws Exception {
            // given
            Product product = new Product("computer", 1000000, "컴퓨터", "초고속 배송", 100, 2L);

            // when
            Integer stock = product.decreaseStock(1);

            // then
            assertThat(stock).isEqualTo(99);
        }

        @Test
        @DisplayName("상품의 재고가 0개가 되면 isSale 필드를 'N'로 변경한다. N은 '판매 안함'을 의미한다.")
        public void 상품_재고_없음() throws Exception {
            // given
            Product product = new Product("computer", 1000000, "컴퓨터", "초고속 배송", 1, 2L);

            // when
            product.decreaseStock(1);

            // then
            assertThat(product.getStock()).isEqualTo(0);
            assertThat(product.getIsSale()).isEqualTo("N");
        }
    }
}
