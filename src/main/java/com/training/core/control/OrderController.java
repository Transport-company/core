package com.training.core.control;

import com.training.core.Urls;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Ordering delivery")
@RequestMapping(Urls.Orders.FULL)
public interface OrderController {
}
