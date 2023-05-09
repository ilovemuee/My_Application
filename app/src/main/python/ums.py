import openai
import pyttsx3
#Intelligent Response and Interaction System
import requests
import re
import json
from datetime import datetime
# def timetable(date):
#    URL = 'http://localhost/date'
#    mmatched = re.search(r'\d{2}-\d{2}-\d{4}',date)
#    res = datetime.strptime(mmatched.group(), '%d-%m-%Y').date()
#    username ="chatgpt"
#    password = "adminPasswordSKM"
#    dic = {"username":username,"password":password,"data":res}
#    r = requests.post(url = URL, params = dic)
# def current_attendence(current):
#    URL = 'http://localhost/current_attendence'
#    username ="chatgpt"
#    password = "adminPasswordSKM"
#    dic = {"username":username,"password":password,"data":regno}
#    r = requests.post(url = URL, params = dic)
#    return r


# def attendance(reg_no):
#    url = 'http://192.168.137.1/gettingitright.php'
#    data = {'reg_no':reg_no}
#    r = requests.post(url=url, data=data)

# def connect(connec,device,status):
#    URL = 'http://192.168.43.205/'+connec
#    username ="chatgpt"
#    password = "adminPasswordSKM"
#    dic = {"username":username,"password":password,"data":device,"status":status}
#    r = requests.post(url = URL, params = dic)
def jarvis(text):
    openai.api_key = "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"
    a = text
    prompt = (a)
    tokenized_text = text.split()
    message = ""
    for token in tokenized_text:
        if token.lower() == "open" and "fan" in [t.lower() for t in tokenized_text]:
            message = "-1"
            break
        elif token.lower() == "what" and "name" in [t.lower() for t in tokenized_text]:
            message = "My name is IRIS"
            break
        elif token.lower() == "open" and "door" in [t.lower() for t in tokenized_text]:
            message = "Opening the door."
            break
        elif token.lower() == "open" and "fan" in [t.lower() for t in tokenized_text]:
                    message = "Opening the fan."
                    break
    if not message:
        openai.api_key = "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"
        prompt = (text)
        response = openai.Completion.create(
            engine="text-davinci-003",
            prompt=prompt,
            max_tokens=1024,
            temperature=0.5,
        )
#         def SpeakText(command):
#                 engine = pyttsx3.init()
#                 engine.say(command)
#                 engine.runAndWait()

        message = response.choices[0].text
        # URL = "http://192.168.43.205/comment"
        # username ="chatgpt"
        # password = "verysecurepassword"
        # comment=message
        # dic = {"username":username,"password":password,"comment":comment}
        # r = requests.post(url = URL, params = dic)
    return message
def ums_assistant(query,reg_no):
        # o = jarvis(f"here is the sentence '{a}' and tell me which date i have mentioned in this sentence and convert it into this format dd-mm-yyyy")
        a = jarvis(f"here is the sentence '{query}' and tell me if this sentence is taking about getting the current Time Table of the student using the app ")
        b = jarvis(f"here is the sentence '{query}' tell me if this sentence is taking about the getting the current information about the  attedence using the app")
        c = jarvis(f"here is the sentence '{query}' tell me if this sentence is taking about  the marking of student attendnce but not asking information about it using the app")
        a1 = True if "Yes" in a else False
        b1 = True if "Yes" in b else False
        c1 = True if "Yes" in c else False
        if c1:
            url = 'http://192.168.137.1/gettingitright.php'
            data = {'reg_no':reg_no}
            r = requests.post(url=url, data=data)
            return "ur attendence has been marked"
        elif a1:
            # timetable(a)
            # set the URL of the PHP script that returns the timetable data as JSON
            url = 'http://192.168.137.1/first.php'
            # send a POST request to the URL with the required parameters
            para = {'reg_no':reg_no}
            response = requests.post(url, data=para)
            # parse the JSON data returned by the PHP script
            timetable = json.loads(response.text)
            # print the timetable data
            return timetable
        elif b1:
             url = 'http://192.168.137.1/attendence.php'
                           # send a POST request to the URL with the required parameters
             para = {'reg_no':reg_no}
             response = requests.post(url, data=para)
                           # parse the JSON data returned by the PHP script
             timetable = json.loads(response.text)
             return ("ur current attendance "+timetable[0]['percentage'])
        else:
            return "please select a valid option"

