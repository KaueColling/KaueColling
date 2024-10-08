package net.weg.topcare.controller.dto.product;

import net.weg.topcare.controller.dto.rating.GeneralRatingGetDTO;
import net.weg.topcare.entity.Image;

public record ProductMinimalGetDTO(
        Long id,
        String name,
        Double price,
        Integer discount,
        String image,
        GeneralRatingGetDTO generalRating
) {}
