package com.example.ui;

import com.example.anno.AddLog;
import com.example.anno.AddLogImple;
import com.example.entity.ProductCatalogItem;
import com.example.repo.ProductRepository;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Route("products")
@PageTitle("ProductGrid")
public class ProductGrid  extends HorizontalLayout {
    private final ProductRepository productRepository;
    Grid<ProductCatalogItem> grid = new Grid<ProductCatalogItem>();
    TextField searchField = new TextField();

    AddLogImple addLogImple;
    ProductDrawerForm productDrawerForm;

    public ProductGrid(ProductRepository productRepository){
        addLogImple = new AddLogImple();
        this.productRepository = productRepository;
        this.setSizeFull();
        setSpacing(false);
        createGridBox();
        createSearchBox();
        productDrawerForm = new ProductDrawerForm();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(searchField,grid);

        setFlexShrink(0, productDrawerForm);

        add(verticalLayout);
        add(productDrawerForm);
        addLogImple.apply(this);
    }

    @AddLog
    private void createGridBox() {

        grid.setSizeFull();

        grid.addColumn(ProductCatalogItem::getName)
                .setHeader("Name")
                .setSortProperty("name");
        grid.addColumn(ProductCatalogItem::getPrice)
                .setHeader("Price")
                .setTextAlign(ColumnTextAlign.CENTER)
                .setSortProperty("price");
        grid.addColumn(ProductCatalogItem::getDescription)
                .setHeader("Description");
        grid.addColumn(ProductCatalogItem::getCategory)
                .setHeader("Category")
                .setSortProperty("category");
        grid.addColumn(ProductCatalogItem::getBrand)
                .setHeader("Brand");

        grid.addSelectionListener(e -> {
            var productDetails = e.getFirstSelectedItem()
                    .flatMap(item -> productRepository
                            .findById(item.getProductId()))
                    .orElse(null);
            productDrawerForm.setProductDetails(productDetails);
        });

        //search logic
//        Pageable pageable = PageRequest.of(1,2);
//        grid.setItemsPageable(pageable -> productRepository
//                .findByNameContainingIgnoreCase(searchField.getValue(),
//                        pageable)
//                .getContent()
//        );



//        this.add(grid);
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<ProductCatalogItem> page = productRepository.findAll(pageable);
//        List<ProductCatalogItem> items = page.getContent();
//        grid.setItems(items);
//
//
//        grid.setItems(query -> {
//            int offset = query.getOffset();
//            int limit = query.getLimit();
//            int page = offset / limit;
//            Pageable pageable = PageRequest.of(page, limit);
//            Page<ProductCatalogItem> result = productRepository.findAll(pageable);
//            return result.getContent().stream();
//        });


//        grid.setItemsPageable(pageable -> productRepository.findAll());//error not more tha 50

    }

    @AddLog
    private void createSearchBox() {
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());

        searchField.addValueChangeListener(e ->
                grid.getDataProvider().refreshAll());
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
    }

    @AddLog
    private String check(){
        return "Hi";
    }

    @AddLog
    private ProductCatalogItem check2(){
        return new ProductCatalogItem();
    }

}
