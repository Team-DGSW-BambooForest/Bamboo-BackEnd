package com.bamboo.postservice;

import com.bamboo.postservice.domain.HashTag;
import com.bamboo.postservice.domain.Post;
import com.bamboo.postservice.domain.repository.HashTagRepository;
import com.bamboo.postservice.domain.repository.PostRepository;
import com.bamboo.postservice.presentation.dto.request.PostRequest;
import com.bamboo.postservice.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class PostServiceApplicationTests {
	@Test
	void contextLoads() {

	}



}
