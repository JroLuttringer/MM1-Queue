
nb_lines = 0
duree=10

r_debit =0
r_nb =0
r_ss_att=0
r_occ=0
esp=0
r_tps_sej=0
s_debit=0
s_sans_att=0
s_occ=0
s_little=0
s_tps_sej=0
s_nb = 0

with open("log") as f_in:
    variable = [
        "lamba" , "mu","duree","ro","r_nb_c","r_ss_att","r_occ","r_debit",
        "esp","r_tps_sej","s_nb_c", "s_ss_att", "s_occ", "s_debit",
        "s_little", "s_tps_sej"
    ]
    for l in f_in.readlines():
        data = l.split(',')
        d = dict(list(zip(variable, data)))
        if d["duree"] == duree:
            r_debit = r_debit + float(d["r_debit"])
            r_nb = r_nb+float(alld["r_nb_c"])
            r_ss_att = r_ss_att +float(d["r_ss_att"])
            r_occ = r_occ+float(d["r_occ"])
            esp = esp + float(d["esp"])
            r_tps_sej = r_tps_sej+float(d["r_tps_sej"])
            s_debit = s_debit + float(a["s_debit"])
            s_occ = s_occ+float(d[s_occ])
            s_little = s_little+float(d["s_little"])
            s_tps_sej = s_tps_sej +float(d["s_tps_sej"])
            s_nb = s_nb+float(d["s_nb_c"])
            nb_lines = nb_lines+1
    v = (s_debit-r_debit)**2 + (r_nb-s_nb)**2 +(r_ss_att-s_sans_att)**2
        + (r_occ-s_occ)**2 + (esp-s_little)**2 + (r_tps_sej-s_tps_sej)**2
    v = v / nb_lines
    print(v)
