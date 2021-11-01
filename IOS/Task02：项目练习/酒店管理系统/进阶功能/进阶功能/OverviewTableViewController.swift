//
//  OverviewTableViewController.swift
//  进阶功能
//
//  Created by mac on 2020/8/12.
//  Copyright © 2020 Yurk. All rights reserved.
//

import UIKit
struct Registration {
    var name: String
    var telNumber: String
    var IDNumber: String
    var checkInDate: Date
    var checkOutDate: Date
    var numberOfAdults: Int
    var numberOfChildren: Int
    var roomType: RoomType
    var wifi: Bool
    }

class OverviewTableViewController: UITableViewController {
    
    var registrations: [Registration] = []
    //registration是继承自1页面
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    //新单元只有一个section
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return registrations.count
    }
    
    //新建cell，在新d页面中想要显示什么格式就怎么定义cell
    override func tableView(_ tableView: UITableView, cellForRowAt
    indexPath: IndexPath) -> UITableViewCell {
        let cell:UITableViewCell = UITableViewCell.init(style: UITableViewCell.CellStyle.subtitle, reuseIdentifier: nil)
    
        let registration = registrations[indexPath.row]
        
        //dateFormatter为自带
        let dateFormatter = DateFormatter()
        dateFormatter.dateStyle = .short
    
        cell.textLabel?.text = registration.name + " " + registration.telNumber
        cell.detailTextLabel?.text = dateFormatter.string(from: registration.checkInDate) + " 至 " + dateFormatter.string(from: registration.checkOutDate) + ": " + registration.roomType.name + " 身份证号:" + registration.IDNumber
        return cell
    }
    
    @IBAction func unwindFromAddRegistration(unwindSegue: UIStoryboardSegue) {
        //添加回退，先定义回退，后进行button的对接
        guard let tableViewController = unwindSegue.source as? TableViewController,
        let registration = tableViewController.registration else { return }
        registrations.append(registration)
        tableView.reloadData()
    }
    
    
    
    
    
}

