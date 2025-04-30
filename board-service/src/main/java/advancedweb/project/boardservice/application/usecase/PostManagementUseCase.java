package advancedweb.project.boardservice.application.usecase;

import advancedweb.project.boardservice.application.dto.request.WritePostReq;
import advancedweb.project.boardservice.application.dto.response.PostDetailRes;
import advancedweb.project.boardservice.application.dto.response.PostSummaryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostManagementUseCase {

    // DI


    // Method
    public Page<PostSummaryRes> readAll(Integer page) {
        return null;
    }

    public List<PostSummaryRes> readRecentPost() {
        return null;
    }

    public PostDetailRes read(String postNo, String userNo) {
        return null;
    }

    public void write(String userNo, WritePostReq request) {

    }

    public void delete(String postNo, String userNo) {

    }
}
