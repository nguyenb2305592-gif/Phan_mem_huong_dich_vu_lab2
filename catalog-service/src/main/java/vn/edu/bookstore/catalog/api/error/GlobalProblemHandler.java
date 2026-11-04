package vn.edu.bookstore.catalog.api.error;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.edu.bookstore.catalog.service.BookNotFoundException;

/** Mọi lỗi → application/problem+json (RFC 9457). */
@RestControllerAdvice
public class GlobalProblemHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    ProblemDetail notFound(BookNotFoundException e, HttpServletRequest req) {
        ProblemDetail p = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        p.setType(ProblemTypes.BOOK_NOT_FOUND);
        p.setTitle("Không tìm thấy sách");
        p.setInstance(URI.create(req.getRequestURI()));
        return p;
    }

    // TODO(TH2-6): bổ sung ánh xạ
    //   DuplicateIsbnException → 409; PreconditionFailedException → 412; PreconditionRequiredException → 428;
    //   ConstraintViolationException (tham số sai mẫu) → 400 validation-failed kèm "errors";
    //   override handleMethodArgumentNotValid (thân JSON sai hợp đồng) → 400 validation-failed;
    //   override handleHttpMessageNotReadable (JSON hỏng) → 400 validation-failed.
}
