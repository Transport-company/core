package com.training.core.control;

import com.training.core.Urls;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Feedback on delivery")
@RequestMapping(Urls.Feedbacks.FULL)
public interface FeedbackController {
}
