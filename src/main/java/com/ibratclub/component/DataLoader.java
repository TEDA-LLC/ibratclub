package com.ibratclub.component;

import com.ibratclub.model.Bot;
import com.ibratclub.model.Category;
import com.ibratclub.model.Company;
import com.ibratclub.model.Department;
import com.ibratclub.repository.BotRepository;
import com.ibratclub.repository.CategoryRepository;
import com.ibratclub.repository.CompanyRepository;
import com.ibratclub.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Malikov Azizjon  *  17.01.2023  *  22:16   *  IbratClub
 */

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final BotRepository botRepository;
    @Value("${spring.sql.init.mode}")
    String mode;
    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) {
        if (mode.equals("always")) {

//            Bot bot = new Bot();
//            bot.setToken("5432072116:AAHHjQHDP-IBzzQdiRyzHhqValr5tKQ6tlI");
//            bot.setUsername("tedauz_bot");
//            Bot bot1 = new Bot();
//            bot1.setUsername("Uzb_Namaz_Times_bot");
//            bot1.setToken("5082104093:AAEtEN22_VScHuRU7mgdvI8H-1p6v8hn8KU");
            Bot bot2 = new Bot();
            bot2.setUsername("ibratclub_bot");
            bot2.setToken("5927728152:AAExhfEpagD__0D9A6b_qJs56SuXV06oZ-8");
//            botRepository.save(bot);
//            botRepository.save(bot1);
            Bot save = botRepository.save(bot2);
            Category service = Category.builder()
                    .nameUz("Xizmatlar \uD83D\uDEE0")
                    .nameRu("Услуги \uD83D\uDEE0")
                    .nameEn("Services \uD83D\uDEE0")
                    .build();
            Category system = Category.builder()
                    .nameUz("Tadbirlar \uD83D\uDCBB")
                    .nameRu("Выставки \uD83D\uDCBB")
                    .nameEn("Events \uD83D\uDCBB")
                    .build();
//            system.setBot(save);
//            service.setBot(save);
            Department department = new Department();
            department.setBot(save);
            Department departmentSave = departmentRepository.save(department);
            system.setDepartment(departmentSave);
            service.setDepartment(departmentSave);
            save.setDepartment(departmentSave);
            botRepository.save(bot2);
            categoryRepository.save(service);
            categoryRepository.save(system);
            Company company = new Company();
            company.setDepartmentList(List.of(departmentSave));
            company.setName("Ibrat Club");
            companyRepository.save(company);
//            company.setBotList(List.of(save));

        }
    }

}
