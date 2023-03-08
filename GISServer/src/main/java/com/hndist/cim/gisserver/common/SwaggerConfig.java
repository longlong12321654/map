//package com.hndist.cim.gisserver.common;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
///**
// * @author 河南数慧信息技术有限公司
// * @version V1.0.0
// * @ClassName: SwaggerConfig
// * @Description: TODO
// * @date 2022/11/18 08:46
// * @Copyright: www.hndist.com All rights reserved.
// * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
// */
//@Configuration
//@EnableSwagger2WebMvc
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .pathMapping("/")
//                .groupName("CIM - 基础平台")
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.regex("/.*"))
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("CIM基础平台-地图服务")
//                .description("CIM-城市信息模型，以建筑信息模型（BIM）、地理信息系统（GIS）、物联网（IoT）等技术为基础，整合城市地上地下、室内室外、历史现状未来多维多尺度信息模型数据和城市感知数据，构建起三维数字空间的城市信息有机综合体。")
//                .termsOfServiceUrl("http://www.hndist.com/")
//                .contact("it@hndist.com")
//                .version("V-2.2.1")
//                .build();
//    }
//
//}
