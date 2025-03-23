# Bug Report: QA İşleri Konum Filtresi - İstanbul

## Summary  
Insider Kariyer sayfasında QA pozisyonlarını konuma ve departmana göre filtreleyen otomasyon testi sırasında, test "İstanbul, Turkiye" konumunun seçilmesi adımında başarısız olmaktadır. Bu konum, manuel test sırasında görünür ve seçilebilirken, Selenium WebDriver tarafından otomasyon esnasında bulunamamaktadır.

## Steps 
1. https://useinsider.com/ adresine gidilir.  
2. Navigasyon çubuğundan "Company" menüsüne tıklanır ve "Careers" seçilir. 
3. "See All Teams" butonuna tıklanır. 
4. "Quality Assurance" butonuna tıklanır.
5. "See all QA jobs" butonuna tıklanır.  
6. Filtreleme yapılmaya çalışılır:  
   - Konum:Istanbul, Turkiye 
   - Departman:Quality Assurance  
7. Konum seçimi sırasında test hataya düşer.

## Expected Results  
"Istanbul, Turkiye" seçeneği açılır menüde görüntülenmeli ve seçilebilir olmalıdır. Filtreleme başarılı bir şekilde yapılmalı ve ilgili iş ilanları listelenmelidir.

## Actual Results
Selenium WebDriver, "Istanbul, Turkiye" seçeneğini bulamamakta ve `NoSuchElementException` fırlatmaktadır. Bu nedenle test devam edememektedir.

## Error Message (Log Alıntısı)
org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {"method":"xpath","selector":"//li[contains(text(),'Istanbul, Turkiye')]"}


## Additional Explanations
- Eleman dinamik olarak oluşturuluyor olabilir ve test script'i çalıştığında henüz sayfaya (DOM'a) tam olarak yüklenmemiş olabilir.  
- Gerekli bekleme ve scroll işlemleri uygulanmasına rağmen otomasyon sırasında sorun devam etmekteydi.  
- Aynı adımlar manuel olarak denendiğinde herhangi bir problem yaşanmadı.



