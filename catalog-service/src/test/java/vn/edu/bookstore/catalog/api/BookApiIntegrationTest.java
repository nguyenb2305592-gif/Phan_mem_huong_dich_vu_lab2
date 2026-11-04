package vn.edu.bookstore.catalog.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import vn.edu.bookstore.catalog.TestcontainersConfig;

/** Kiểm thử tích hợp với PostgreSQL thật (Testcontainers) + Flyway. */
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfig.class)
class BookApiIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Test
    void layTheoIsbn() throws Exception {
        mvc.perform(get("/api/v1/books/9780134685991"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"));
    }

    // TODO(TH2-7): thêm ca kiểm thử: phân trang + sắp xếp price,desc; tìm "microservices" (3 kết quả);
    //   size=1000 → 400; ETag + If-None-Match → 304; 404 problem; tạo (201 + Location) → trùng (409)
    //   → PUT không If-Match (428) → PUT đúng ETag (200) → PUT ETag cũ (412) → DELETE (204).
}
