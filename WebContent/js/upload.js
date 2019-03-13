
/*Function to handle single file upload control*/
$(document).ready(function(){
	var TxtEventTitleFlag = false; EventGalleryCategoryFlag = false; FileFlag = false; FileExtFlag = false; FileSizeFlag = false;
    
    $("#file").change(function () {
        $("#fileValidate").empty();
        var output = document.getElementById("result");
        /*claculate file size in MB*/
        var FileSize = document.getElementById("file").files[0].size / 1024 / 1024;
        /*check if selected file is null or not*/
        if (document.getElementById("file").files.length == 0) 
        {
            $("#fileValidate").html("Invalid File Size OR No File Selected..!!<br />File Size Must Be Higher Than 0 MB & Lower Than 2 MB..!!");
            FileFlag = false;
        }
        else 
        {   /*extract extention from the file*/
            var extension = document.getElementById("file").value.split('.').pop().toLowerCase();
            var validFileExtensions = ['jpg', 'png'];
            FileFlag = true;
            /*compare if extracted extention is in rquired form or not*/
            if ($.inArray(extension, validFileExtensions) == -1) 
            {
                $("#fileValidate").html("Only .jpg & .png files are acceptable..!!");
                FileExtFlag = false;
            }
            else 
            {
                FileExtFlag = true;
            }
            /*check size of file*/
            if (FileSize > 2) 
            {
                $("#fileValidate").html("File Size Must Be Higher Than 0 MB & Lower Than 2 MB..!!");
                FileSizeFlag = false;
            }
            else 
            {
                FileSizeFlag = true;
            }
        }
        if (FileFlag == true && FileSizeFlag == true && FileExtFlag == true) 
        {
            /*var reader = new FileReader();
            reader.onload = function (e) 
            {
                document.getElementById("i1").src = e.target.result;
            };
            reader.readAsDataURL(document.getElementById("file").files[0]);*/
        	var picReader = new FileReader();
            picReader.addEventListener("load",function(event){
	            var picFile = event.target;
	            var div = document.createElement("div");
	            div.innerHTML = "<img class='thumbnail' src='" + picFile.result + "'" + "title='" + picFile.name + "'/>";
	            output.innerHTML = '';
	            output.insertBefore(div,null);
            });
            picReader.readAsDataURL(document.getElementById("file").files[0]);
        }
        else 
        {
            document.getElementById("i1").src = "default.jpg";
        }
    });

});


/*function to handle multiple file upload*/
/*window.onload = function()
{
        var filesInput = document.getElementById("multiplefile");
        filesInput.addEventListener("change", function(event){
        var files = event.target.files; 
        var output = document.getElementById("result");
        /*compare if extracted extention is in rquired form or not
        var validFileExtensions = ['jpg', 'png'];
        for(var i = 0; i< files.length; i++)
        {
            var file = files[i];
            /*claculate file size on MB
            var filesize = file.size/1024/1024;
            var filename = file.name;
            var extension = filename.substring(filename.lastIndexOf('.') +1).toLowerCase();
            if (filesize < 2 && (extension == 'jpg' || extension == 'png')) 
            {
                var picReader = new FileReader();
                picReader.addEventListener("load",function(event){
                var picFile = event.target;
                var div = document.createElement("div");
                div.innerHTML = "<img class='thumbnail' src='" + picFile.result + "'" +
                "title='" + picFile.name + "'/>";
                output.insertBefore(div,null);
            });
            
                picReader.readAsDataURL(file);
            }
            /*reject invalid files
            else
            {   
                var s1 = '';
                var s2 = document.getElementById("multiplefileValidate").innerHTML;
                var s1 = filename+" "+"Invalid Input<br/>";
                document.getElementById("multiplefileValidate").innerHTML = s1 + s2;
            }
        }
    });
}  
*/