package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.rating.GeneralRatingGetDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Brand brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    private Integer generalRating;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductSpecification> specifications = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVarietions> productVarietions = new ArrayList<>();

    @OneToMany
    private List<Image> images = new ArrayList<>();

    @Column
    private Integer discount = 0;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "product")
    private List<Rating> ratings;


    public Product(ProductPostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }

    public ProductGetDTO toGetDTO(){
        return new ProductGetDTO(
                this.id,
                this.brand,
                this.name,
                this.price,
                this.categories,
                new GeneralRatingGetDTO(this.generalRating, (long) this.ratings.size()),
                this.discount,
                this.description,
                this.specifications.stream().map(ProductSpecification::toGetDTO).toList(),
//                this.productVarietions.stream().map(ProductVarietions::toGetDTO).toList(),
                this.images.stream().map(Image::toString).toList(),
                this.stock
        );
    }

    public ProductMinimalGetDTO toMinimalGetDTO(){
        String image = "";
        if (!this.images.isEmpty()) {
            image = this.images.get(1).toString();
        }
        return new ProductMinimalGetDTO(
            this.id,
            this.name,
            this.price,
            this.discount,
            image,
            new GeneralRatingGetDTO(this.generalRating, (long) this.ratings.size())
        );
    }

}
