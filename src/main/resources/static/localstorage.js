/**
 * 
 */
var storage=window.localStorage;
var thedate = new Array()
var firstname = new Array()
var yourname = new Array()
var gender = new Array()
var nickname = new Array()
var phone = new Array()
var email = new Array()
var area = new Array()

function GetRadioValue(RadioName){
	var obj;    
	obj=document.getElementsByName(RadioName);
	if(obj!=null){
		var i;
		for(i=0;i<obj.length;i++){
			if(obj[i].checked){
				return obj[i].value;            
		    }
		}
	}
		 return null;
}
		
		
function store() {
	if (!window.localStorage) {
		alert(" Your browser not spport localstorage.");
	} else {
		if (storage.length==0) {
			thedate = new Array()
			firstname = new Array()
			yourname = new Array()
			gender = new Array()
			nickname = new Array()
			phone = new Array()
			email = new Array()
			area = new Array()
		}
		thedate.push(new Date())
		firstname.push(document.getElementById("firstname").value)
		yourname.push(document.getElementById("name").value)
		gender.push(GetRadioValue("gender"))
		nickname.push(document.getElementById("nickname").value)
		phone.push(document.getElementById("phone").value)
		email.push(document.getElementById("email").value)
		area.push(document.getElementById("area").value)
		storage.setItem("time",JSON.stringify(thedate))
		storage.setItem("firstname",JSON.stringify(firstname));
		storage.setItem("name",JSON.stringify(yourname));
		storage.setItem("gender",JSON.stringify(gender));
		storage.setItem("nickname",JSON.stringify(nickname));
		storage.setItem("phone",JSON.stringify(phone));
		storage.setItem("email",JSON.stringify(email));
		storage.setItem("area",JSON.stringify(area));
		alert("Save successfully!")
	}
}
function cleardat() {
	alert(thedate.length)
	thedate=[]
	storage.clear()
	
	var a = document.getElementById("data");
	a.innerHTML=""
}
function displayit() {
	var a = document.getElementById("data");
	var alldate=""
	var data=""
	thedate = JSON.parse(storage.getItem("time"))
	firstname = JSON.parse(storage.getItem("firstname"))
	yourname = JSON.parse(storage.getItem("name"))
	gender = JSON.parse(storage.getItem("gender"))
	nickname =JSON.parse(storage.getItem("nickname"))
	phone = JSON.parse(storage.getItem("phone"))
	email = JSON.parse(storage.getItem("email"))
	area =JSON.parse(storage.getItem("area"))
	 
	for (var i = 0; i < thedate.length; i++) {
		data += thedate[i]+" "+firstname[i]+" "+yourname[i]+" "+gender[i]+" "+nickname[i]+" "+phone[i]+" "+email[i]+" "+area[i]+"<br>"
	}
	a.innerHTML=data;
}
			