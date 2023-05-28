package com.example.myapplication.entity

import com.example.myapplication.data.data_classes.Camera
import com.example.myapplication.data.data_classes.Rover

interface Photo {
    var id: Int?
    var sol: Int?
    var camera: Camera?
    var imgSrc: String?
    var earthDate: String?
    var rover: Rover?
}