package com.gcs.firewall.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirewallScript {

	public static String Connect(Long server_id, String Task, String username,String password, String port) {
    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,username,password,port);
    processBuilder.redirectErrorStream(true);
    String s="";
    Process process = null;
		try {
			process = processBuilder.start();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
        BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(process.getInputStream()));
    try {
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	return s;
}
	public static String from_desktop(Long server_id, String Task, String ip_address,String port, String protocol, String action) {
    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,ip_address,port,protocol,action);
    processBuilder.redirectErrorStream(true);
    String s="";
    Process process = null;
		try {
			process = processBuilder.start();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
        BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(process.getInputStream()));
    try {
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	return s;
}
	public static String deny(Long server_id, String Task, Long rule_id,Long user_id) {
    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,""+rule_id,""+user_id);

    processBuilder.redirectErrorStream(true);
    String s="";
    Process process = null;
		try {
			process = processBuilder.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(process.getInputStream()));
    try {
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	return s;
}
	public static String check_os(Long server_id, String Task) {
    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);

    processBuilder.redirectErrorStream(true);
    String s="";
    Process process = null;
		try {
			process = processBuilder.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(process.getInputStream()));
    try {
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	return s;
}
	public static String add(Long server_id, String Task, String ip_address,String protocol, String port,String action) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,ip_address,protocol,port,action);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String edit(Long server_id, String Task, Long ruleId,String new_protocol,String new_ip_address, String new_port,String new_action) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,""+ruleId,new_protocol,new_ip_address,new_port,new_action);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String delete(Long server_id, String Task, Long id_rule) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,""+id_rule);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String list(Long server_id, String Task) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);
	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}	
	public static String check(Long server_id, String Task, String r_ip_address, String r_action) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,r_ip_address,r_action);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}	
	public static String accesslogs(Long server_id, String Task) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String securelogs(Long server_id, String Task) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}	
	public static String csf(Long server_id, String Task) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String openedports(Long server_id, String Task) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String services(Long server_id, String Task) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String startservices(Long server_id, String Task, String service) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,service);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String restartservices(Long server_id, String Task, String service) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,service);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String stopservices(Long server_id, String Task, String service) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,service);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String checkport(Long server_id, String Task, String host, String port) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,host,port);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String checkip(Long server_id, String Task, String host) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,host);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String installservice(Long server_id, String Task, String service) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,service);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String fail2banlogs(Long server_id, String Task) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String fail2banIPbanned(Long server_id, String Task) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String addurl(Long server_id, String Task, String url_to_protect, String ip_add) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,url_to_protect,ip_add);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String updateurl(Long server_id, String Task,Long id_url, String url2, String ip_address2) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,""+id_url,url2,ip_address2);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String deleteurl(Long server_id, String Task,String url) {
	    ProcessBuilder processBuilder = new ProcessBuilder("python3","/home/firewal/public_html/scripts/firewall.py",""+server_id,Task,url);

	    processBuilder.redirectErrorStream(true);
	    String s="";
	    Process process = null;
			try {
				process = processBuilder.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(process.getInputStream()));
	    try {
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
