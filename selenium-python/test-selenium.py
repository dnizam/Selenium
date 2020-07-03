from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from random import seed
import random
#from random import randint
from selenium.webdriver.support.ui import WebDriverWait
from selenium.common.exceptions import NoSuchElementException
# seed random number generator
#seed(1)
rand = random.randint(0, 50)
#rand = 4
email = "test"+str(rand)+"@test.com"
print(str(rand))
CHROMEDRIVER_PATH = '/usr/local/bin/chromedriver'
WINDOW_SIZE = "1920,1080"
chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--window-size=%s" % WINDOW_SIZE)
chrome_options.add_argument('--no-sandbox')
driver = webdriver.Chrome(executable_path=CHROMEDRIVER_PATH,
                          chrome_options=chrome_options
                         )
driver.get("http://35.232.10.226:8085/getForm")
wait = WebDriverWait( driver, 8 )
element = driver.find_element_by_name("employeeName")
element.send_keys("test"+str(rand))
wait = WebDriverWait( driver, 5 )
element = driver.find_element_by_name("employeeEmail")
element.send_keys(email)
wait = WebDriverWait( driver, 5 )
print("email entered is: "+element.text)
wait = WebDriverWait( driver, 5 )
submit = driver.find_element_by_xpath("/html/body/form/input[3]")
submit.click()
wait = WebDriverWait( driver, 8 )
element = driver.find_element_by_xpath("/html/body/h4[1]")
print(element.text)
#print(driver.title)


#validate if the entry is updated in database
backendURL = "http://35.224.103.187:8082/user/get-by-email?email="+"test"+str(rand)+"@test.com"
print(backendURL)
driver.get(backendURL)
wait = WebDriverWait( driver, 10 )
#print("the broser title is: "+str(driver.title))
try:
  element = driver.find_element_by_xpath("/html/body")
except NoSuchElementException:
    print("No element found")
msgText = element.text
print(str(msgText))
#assert msgText.startswith('The user id is')
driver.close()
