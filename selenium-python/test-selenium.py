from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from random import seed
from random import random
# seed random number generator
seed(1)
rand = random()
CHROMEDRIVER_PATH = '/usr/local/bin/chromedriver'
WINDOW_SIZE = "1920,1080"
chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--window-size=%s" % WINDOW_SIZE)
chrome_options.add_argument('--no-sandbox')
driver = webdriver.Chrome(executable_path=CHROMEDRIVER_PATH,
                          chrome_options=chrome_options
                         )
driver.get("http://35.239.244.72:8085/getForm")
element = driver.find_element_by_name("employeeName")
element.send_keys("test"+rand)
element = driver.find_element_by_xpath("/html/body/form/input[3]")
element.send_keys("test"+rand+"@test.com")

#validate if the entry is updated in database
driver.get("http://104.197.190.255:8082/user/get-by-email?email="+"test"+random+"@test.com")
element = driver.find_element_by_xpath("/html/body")
msgText = element.get_attribute('value')
assert msgText !=  "User not found"
driver.close()
