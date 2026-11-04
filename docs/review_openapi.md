| Thao tác | Mã thành công | Mã lỗi đã khai báo | An toàn? | Idempotent? |
|---|---|---|---|---|
| GET /api/v1/books | 200 | 400 | có | có |
| POST /api/v1/books | 201 + Location + ETag | 400, 409 | không | không |
| GET /api/v1/books/{isbn} | 200 + ETag / 304 | 404 | có | có |
| PUT /api/v1/books/{isbn} | 200 + ETag mới | 400, 404, 412, 428 | không | có |
| DELETE /api/v1/books/{isbn} | 204 | 404 | không | có |
