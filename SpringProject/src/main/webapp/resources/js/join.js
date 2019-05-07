/**
 * 
 */

var correctCaptcha = function(response) {
        console.log();

    };
    var onloadCallback = function() {
        grecaptcha.render('html_element', {
            'sitekey' : '6LcdI5gUAAAAAJDyzRHp9UC344sNOBYGKcsvRA_q',
            'callback' : correctCaptcha
        });
    };
    
    
    $(document).ready (function() {
    	
    	$("#id").focus(function() {
    		$("#idwrap").css("border-color","#d30923");
    		$("#pwwrap").css("border-color","#dadada");
    		$("#pwchwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#dadada");
    		$("#eMailwrap").css("border-color","#dadada");
    		$("#genderwrap").css("border-color","#dadada");
    		$("#yearwrap").css("border-color","#dadada");
    		$("#monthwrap").css("border-color","#dadada");
    		$("#daywrap").css("border-color","#dadada");
    		
    		$('#id').keyup(function(){
    			 if($("#id").val().length == 0) {
    				 $('font[name=id]').text('');
    				  $('font[name=id]').html("필수 정보입니다.");
    			}  else if($("#id").val().length < 4) {
    				$('font[name=id]').text('');
    				$('font[name=id]').html("아이디는 4글자 이상이어야 합니다.");
    				$('font[name=id]').css("color", "red");
    			} else {
    				$('font[name=id]').text('');
    			}
    		 });
    	});
    	$("#id").focusout(function() {
    		var idchec = $("#reg_frm").serialize();
    	    $.ajax({
    	        // url : "/jsp19/lginOK.jsp",
    	        url : "idcheckOk",
    	        type : "post",
    	        dataType : "text",
    	        data : idchec,
    			success : function(json) {
    				//console.log(json);
    				var result = JSON.parse(json);

    				if(result.code == "success") {
    					$('font[name=id]').text('');
    				    $('font[name=id]').html(result.desc);
    				    $('font[name=id]').css("color", "#00cc27");
    				    if($("#id").val().length == 0) {
    						$('font[name=id]').text('');
    						$('font[name=id]').html("필수 정보입니다.");
    						$('font[name=id]').css("color", "red");
    						} else if($("#id").val().length < 4) {
    							$('font[name=id]').text('');
    							$('font[name=id]').html("아이디는 4글자 이상이어야 합니다.");
    							$('font[name=id]').css("color", "red");
    						}
    	           } else {
    	        	   $('font[name=id]').text('');
    				   $('font[name=id]').html(result.desc);
    				   $('font[name=id]').css("color", "red");
    	           }
    	        }
    		}); 
    	});
    	
    	$("#pw").focus(function() {
    		$("#pwwrap").css("border-color","#d30923");
    		$("#pwchwrap").css("border-color","#dadada");
    		$("#idwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#dadada");
    		$("#eMailwrap").css("border-color","#dadada");
    		$("#genderwrap").css("border-color","#dadada");	
    		$("#yearwrap").css("border-color","#dadada");
    		$("#monthwrap").css("border-color","#dadada");
    		$("#daywrap").css("border-color","#dadada");
    		
    		$('#pw').keyup(function(){
    			  if($("#pw").val().length == 0) {	  
    				  $('font[name=pw]').text('');
    				  $('font[name=pw]').html("필수 정보입니다.");
    				} else {
    					$('font[name=pw]').text('');
    				}
    		  });
    	});
    	
    	
    	$("#pw_check").focus(function() {
    		$("#pwchwrap").css("border-color","#d30923");
    		$("#pwwrap").css("border-color","#dadada");
    		$("#idwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#dadada");
    		$("#eMailwrap").css("border-color","#dadada");
    		$("#genderwrap").css("border-color","#dadada");
    		$("#yearwrap").css("border-color","#dadada");
    		$("#monthwrap").css("border-color","#dadada");
    		$("#daywrap").css("border-color","#dadada");
    		
    		 $('#pw_check').keyup(function(){
    			   if($('#pw').val()==$('#pw_check').val()){
    				   $('font[name=pw_check]').text('');
    				    $('font[name=pw_check]').html("비밀번호가 일치합니다");
    				    $('font[name=pw_check]').css("color", "#00cc27");
    				}else {
    					$('font[name=pw_check]').text('');
    					$('font[name=pw_check]').html("비밀번호가 일치하지 않습니다.");
    					$('font[name=pw_check]').css("color", "red");
    				}
    			   if($('#pw_check').val().length == 0) {
    				   $('font[name=pw_check]').text('');
    				    $('font[name=pw_check]').html("필수 정보입니다.");
    				    $('font[name=pw_check]').css("color", "red"); 
    			   }
    		  }); 
    	});
    	
    	// 포커스아웃 pw_check 내용 비교
    	  $('#pw_check').focusout(function() {
    		  if($("#pw").val().length == 0) {	  
    			  $('font[name=pw_check]').text('');
    			  $('font[name=pw_check]').html("필수 정보입니다.");
    			} else {
    				$('font[name=pw_check]').text('');
    			}

    			if($('#pw').val()!=$('#pw_check').val()){
    				  $('#pw_check').val("");
    			 }
    			if($('#pw').val()==$('#pw_check').val()){
    				   $('font[name=pw_check]').text('');
    				    $('font[name=pw_check]').html("비밀번호가 일치합니다");
    				    $('font[name=pw_check]').css("color", "#00cc27");
    				}else {
    						 $('font[name=pw_check]').text('');
    					    $('font[name=pw_check]').html("비밀번호가 일치하지 않습니다.");
    					    $('font[name=pw_check]').css("color", "red");
    				}
    			   if($('#pw_check').val().length == 0) {
    				   $('font[name=pw_check]').text('');
    				    $('font[name=pw_check]').html("필수 정보입니다.");
    				    $('font[name=pw_check]').css("color", "red");
    				    
    			   }

    	});
    	
    	$("#name").focus(function() {
    		$("#pwchwrap").css("border-color","#dadada");
    		$("#pwwrap").css("border-color","#dadada");
    		$("#idwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#d30923");
    		$("#eMailwrap").css("border-color","#dadada");
    		$("#genderwrap").css("border-color","#dadada");
    		$("#yearwrap").css("border-color","#dadada");
    		$("#monthwrap").css("border-color","#dadada");
    		$("#daywrap").css("border-color","#dadada");
    		
    		$('#name').keyup(function(){
    			  if($("#name").val().length == 0) {	
    				  $('font[name=name]').text('');
    				  $('font[name=name]').html("필수 정보입니다.");
    			  } else if($("#name").val() == "관리자") {
    				  $('font[name=name]').text('');
    				  $('font[name=name]').html("사용 할 수 없는 이름입니다.");
    			  } else {
    				  $('font[name=name]').text('');
    			  }
    		  });
    	});
    	$("#name").focus(function() {
    		if($("#name").val().length == 0) {	
    			  $('font[name=name]').text('');
    			  $('font[name=name]').html("필수 정보입니다.");
    		  } else {
    			  $('font[name=name]').text('');
    		  }
    	});
    	
    	$("#eMail").focus(function() {
    		$("#pwchwrap").css("border-color","#dadada");
    		$("#pwwrap").css("border-color","#dadada");
    		$("#idwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#dadada");
    		$("#eMailwrap").css("border-color","#d30923");
    		$("#genderwrap").css("border-color","#dadada");
    		$("#yearwrap").css("border-color","#dadada");
    		$("#monthwrap").css("border-color","#dadada");
    		$("#daywrap").css("border-color","#dadada");
    		
    		// 메일 필수
    		  $('#eMail').keyup(function(){
    			  if($("#eMail").val().length == 0) {	
    				  $('font[name=eMail]').text('');
    				  $('font[name=eMail]').html("필수 정보입니다.");
    			  } else {
    				  $('font[name=eMail]').text('');
    				  var email = document.getElementById("eMail").value;
    					var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    					if(exptext.test(email)==false){
    						//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우	
    						$('font[name=eMail]').text('');
    						$('font[name=eMail]').html("이 메일형식이 올바르지 않습니다.");
    						$('font[name=eMail]').css("color", "red");
    						return false;
    					} 
    			  }
    			  
    		  });
    	});
    	$("#eMail").focusout(function() {
    		if($("#eMail").val().length == 0) {	
    			  $('font[name=eMail]').text('');
    			  $('font[name=eMail]').html("필수 정보입니다.");
    		  } else {
    			  $('font[name=eMail]').text('');
    		  }
    	});
    	
    	$("#gender").focus(function() {
    		$("#pwchwrap").css("border-color","#dadada");
    		$("#pwwrap").css("border-color","#dadada");
    		$("#idwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#dadada");
    		$("#eMailwrap").css("border-color","#dadada");
    		$("#genderwrap").css("border-color","#d30923");
    		$("#yearwrap").css("border-color","#dadada");
    		$("#monthwrap").css("border-color","#dadada");
    		$("#daywrap").css("border-color","#dadada");
    		
    		if($("#gender option:selected").val() == "man") {
    			$('font[name=gender]').text('');
    		} else if($("#gender option:selected").val() == "woman") {
    			$('font[name=gender]').text('');
    		}
    	});
    	$("#gender").focusout(function() {
    		if($("#gender option:selected").val() == "gen") {
    			$('font[name=gender]').text('');
    			$('font[name=gender]').html("필수 정보입니다,");
    		} else {
    			$('font[name=gender]').text('');
    		}	
    	});
    	
    	
    	$(".year").focus(function() {
    		$("#pwchwrap").css("border-color","#dadada");
    		$("#pwwrap").css("border-color","#dadada");
    		$("#idwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#dadada");
    		$("#eMailwrap").css("border-color","#dadada");
    		$("#yearwrap").css("border-color","#d30923");
    		$("#monthwrap").css("border-color","#dadada");
    		$("#daywrap").css("border-color","#dadada");
    		$("#genderwrap").css("border-color","#dadada");
    		
    		$('.year').keyup(function(){
    			 if($(".year").val().length == 0) {
    				 $('font[name=birth]').text('');
    				  $('font[name=birth]').html("필수정보입니다.");
    			}  else if($(".year").val().length > 4) {
    				$('font[name=birth]').text('');
    				$('font[name=birth]').html("태어난 년도 4자리를 정확하게 입력하세요.");
    				$('font[name=birth]').css("color", "red");
    			} else if($(".year").val().length < 4) {
    				$('font[name=birth]').text('');
    				$('font[name=birth]').html("태어난 년도 4자리를 정확하게 입력하세요.");
    				$('font[name=birth]').css("color", "red");
    			} else {
    				$('font[name=birth]').text('');
    			}
    		 });
    	});
    	
    	$(".year").focusout(function() {
    		if($(".year").val().length == 0) {
    			 $('font[name=birth]').text('');
    			  $('font[name=birth]').html("태어난 년도 4자리를 정확하게 입력하세요..");
    		} else if($(".year").val().length > 4) {
    			$(".year").val("");
    			$('font[name=birth]').text('');
    			$('font[name=birth]').html("태어난 년도 4자리를 정확하게 입력하세요.");
    			$('font[name=birth]').css("color", "red");
    		} else if($(".year").val().length < 4) {
    			$(".year").val("");
    			$('font[name=birth]').text('');
    			$('font[name=birth]').html("태어난 년도 4자리를 정확하게 입력하세요.");
    			$('font[name=birth]').css("color", "red");
    		} else {
    			$('font[name=birth]').text('');
    		}
    	});
    	$(".month").focus(function() {
    		$("#pwchwrap").css("border-color","#dadada");
    		$("#pwwrap").css("border-color","#dadada");
    		$("#idwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#dadada");
    		$("#eMailwrap").css("border-color","#dadada");
    		$("#yearwrap").css("border-color","#dadada");
    		$("#monthwrap").css("border-color","#d30923");
    		$("#daywrap").css("border-color","#dadada");
    		$("#genderwrap").css("border-color","#dadada");
    	});
    	
    	$(".month").focusout(function() {
    		if($(".month option:selected").val() == "month") {
    			$('font[name=birth]').text('');
    			$('font[name=birth]').html("생년월일을 다시 확인해주세요.");
    		} else {
    			$('font[name=birth]').text('');
    		}
    	});
    	
    	$(".day").focus(function() {
    		$("#pwchwrap").css("border-color","#dadada");
    		$("#pwwrap").css("border-color","#dadada");
    		$("#idwrap").css("border-color","#dadada");
    		$("#namewrap").css("border-color","#dadada");
    		$("#eMailwrap").css("border-color","#dadada");
    		$("#yearwrap").css("border-color","#dadada");
    		$("#monthwrap").css("border-color","#dadada");
    		$("#daywrap").css("border-color","#d30923");
    		$("#genderwrap").css("border-color","#dadada");
    		
    		$('.day').keyup(function(){
    			if($(".day").val().length > 2) {
    				$('font[name=birth]').html("태어난 일(날짜) 2자리를 정확하게 입력하세요.");
    				$('font[name=birth]').css("color", "red");
    			} 
    		});
    	});
    	$(".day").focusout(function() {
    		if($(".year").val().length == 0) {
    			 $('font[name=birth]').text('');
    			  $('font[name=birth]').html("태어난 년도 4자리를 정확하게 입력하세요..");
    		} else if($(".year").val().length > 4) {
    			$(".year").val("");
    			$('font[name=birth]').text('');
    			$('font[name=birth]').html("태어난 년도 4자리를 정확하게 입력하세요.");
    			$('font[name=birth]').css("color", "red");
    		} else if($(".year").val().length < 4) {
    			$(".year").val("");
    			$('font[name=birth]').text('');
    			$('font[name=birth]').html("태어난 년도 4자리를 정확하게 입력하세요.");
    			$('font[name=birth]').css("color", "red");
    		} else if($(".day").val().length == 0) {
    			  $('font[name=birth]').html("태어난 일(날짜) 2자리를 정확하게 입력하세요.");
    		} else if($(".day").val().length > 2) {
    			$('font[name=birth]').html("태어난 일(날짜) 2자리를 정확하게 입력하세요.");
    			$('font[name=birth]').css("color", "red");
    			$(".day").val("");
    		} else if($(".day").val().length == 1) {
    			$('font[name=birth]').html("태어난 일(날짜) 2자리를 정확하게 입력하세요.");
    			$('font[name=birth]').css("color", "red");
    			$(".day").val("");
    		} else if($(".month option:selected").val() == "month") {
    			$('font[name=birth]').text('');
    			$('font[name=birth]').html("생년월일을 다시 확인해주세요.");
    		} else {
    			$('font[name=birth]').text('');
    		}
    	});
    	
    	if($('#pw').val()!=$('#pw_check').val()){
    		$('#pw_check').val("");
    	}
    });

    	
    function form_check() {
    	if($("#id").val().length == 0) {
    		 alert("아이디는 필수 정보입니다.");
    		 $("#id").focus();
             return;
    	}
        
        if($("#id").val().length < 4) {
    		 alert("아이디는 4글자 이상이어야 합니다.");
    		 $("#id").focus();
            return;
    	}
        
        if($("#pw").val().length == 0) {
    		 alert("비밀번호는 필수 정보입니다.");
    		 $("#id").focus();
            return;
    	}
        
        
        if($("#pw").val() != $("#pw_check").val()) {
    		  alert("비밀번호가 일치하지 않습니다.");
    		 $("#id").focus();
            return;
    	}
        
        if($("#name").val().length == 0) {
    		 alert("이름은 필수 정보입니다.");
    		 $("#name").focus();
            return;
    	} else if($("#name").val() == "관리자") {
    		alert("사용 할 수 없는 이름입니다.");
    		$("#name").focus();
		  }
        
         if($(".year").val().length == 0) {
        	 alert("태어난 년도 4자리를 정확하게 입력하세요.");
    		 $(".year").focus();
             return; 
         } else if($(".year").val().length == 1) {
        	 alert("태어난 년도 4자리를 정확하게 입력하세요.");
    		 $(".year").focus();
             return; 
         } else if($(".year").val().length == 2) {
        	 alert("태어난 년도 4자리를 정확하게 입력하세요.");
    		 $(".year").focus();
             return; 
         } else if($(".year").val().length == 3) {
        	 alert("태어난 년도 4자리를 정확하게 입력하세요.");
    		 $(".year").focus();
             return; 
         } 
         
         if($(".month option:selected").val() == "month") {
        	 alert("생년월일을 다시 확인해주세요.");
    		 $(".month").focus();
             return; 
         }
         
         if($(".day").val().length == 0) {
        	 alert("태어난 일(날짜) 2자리를 정확하게 입력하세요.");
    		 $(".day").focus();
             return; 
         } else if($(".day").val().length == 1) {
        	 alert("태어난 일(날짜) 2자리를 정확하게 입력하세요.");
    		 $(".day").focus();
             return; 
         }
         
         
         if($("#gender option:selected").val() == "gen") {
        	 alert("성별을 다시 확인해주세요.");
    		 $("#gender").focus();
             return; 
         }
         
         if($("#eMail").val().length == 0) {
    		 alert("메일은 필수 정보입니다.");
    		 $("#eMail").focus();
             return;
    	} else {
    		var email = document.getElementById("eMail").value;
    		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    				if(exptext.test(email)==false){
    			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우			
    			alert("이 메일형식이 올바르지 않습니다.");
    			document.addjoin.email.focus();
    			return false;
    		}
    	}
        if (grecaptcha.getResponse() == ""){

    	     alert("보안검사를 확인해 주세요");

    	     return;

    	 } else {


    	 } 
     	
         submit_ajax();
    }

    function submit_ajax() {
    	var queryString = $("#reg_frm").serialize();
        $.ajax({
            // url : "/jsp19/lginOK.jsp",
            url : "joinOk",
            type : "post",
            dataType : "text",
            data : queryString,
    		success : function(json) {
    			//console.log(json);
    			var result = JSON.parse(json);

    			if(result.code == "success") {
                   alert(result.desc)
                    window.location.replace("login");
               } else {
                    alert(result.desc);
               }
            }
    	}); 
    }